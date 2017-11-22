package org.lotus.carp.configuration;

import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.lotus.carp.security.domain.Action;
import org.lotus.carp.security.repository.ActionRepository;
import org.lotus.carp.security.repository.RoleRepository;
import org.lotus.carp.security.vo.SecurityActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用自定义URL角色、权限过滤
 *
 * @author: Foy Lian
 * Date: 11/22/2017
 * Time: 3:44 PM
 */
@Configuration
@PropertySource(value = "classpath:/config/carp.properties")
@ConfigurationProperties(prefix = "carp.security")
@Component
public class ActionFilterSecurityMetadataSource extends DefaultFilterInvocationSecurityMetadataSource implements
        InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ActionFilterSecurityMetadataSource.class);

    @Setter
    private String publicUrlPattern;

    @Setter
    private String securityUrlPattern;

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RoleRepository roleRepository;


    private Queue<RequestActionMapping<RequestMatcher>> cachedRoleMappings = new ConcurrentLinkedDeque<>();

    public static final ConfigAttribute NO_PERMISSION_ROLE = new SecurityConfig("_ROLE_PRIVATE_");

    public static final ConfigAttribute SECURITY_PERMISSION_ROLE = new SecurityConfig("ROLE_SECURITY");
    private ConfigAttribute securityUrlRole = SECURITY_PERMISSION_ROLE;


    /**
     * Sets the internal request map from the supplied map. The key elements should be of
     * type {@link RequestMatcher}, which. The path stored in the key will depend on the
     * type of the supplied UrlMatcher.
     */
    public ActionFilterSecurityMetadataSource() {
        super(new LinkedHashMap<>());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation invocation = (FilterInvocation) object;
        HttpServletRequest request = invocation.getRequest();
        //  白名单
        if (StringUtils.isNotEmpty(publicUrlPattern) && invocation.getRequestUrl().matches(publicUrlPattern)) {
            return null;
        }
        for (RequestActionMapping<RequestMatcher> mapping : cachedRoleMappings) {
            RequestMatcher matcher = mapping.getMatcher();
            if (matcher.matches(request)) {
                mapping.getAction().getGrantedRoles();
            }
        }
        // 兜底名单（表示既不是白名单，有没有功能授权，则匹配一个ROLE_SECURITY，交给角色定义来决定怎么处理，一般不处理则不能访问这个URL
        if (StringUtils.isNotEmpty(securityUrlPattern) && invocation.getRequestUrl().matches(securityUrlPattern)) {
            return Collections.singleton(securityUrlRole);
        }
        return null;
    }

    public boolean reload() {
        Queue<RequestActionMapping<RequestMatcher>> tmpCachedRoleMappingsQueue = new ConcurrentLinkedQueue<RequestActionMapping<RequestMatcher>>();
        List<Action> actionList = actionRepository.listByLeaf();
        if (CollectionUtils.isNotEmpty(actionList)) {
            actionList.forEach(action -> {
                SecurityActionResult actionResult = new SecurityActionResult();
                BeanUtils.copyProperties(action, actionResult);
                List<String> roleCodes = roleRepository.listRoleCodeCanAccessActionId(action.getId());
                if (CollectionUtils.isNotEmpty(roleCodes)) {
                    roleCodes.forEach(code -> {
                        actionResult.getGrantedRoles().add(new SecurityConfig(code));
                    });
                } else {
                    actionResult.getGrantedRoles().add(NO_PERMISSION_ROLE);
                }

                String httpMethod = action.getActionMethod();
                RequestMatcher matcher = new RegexRequestMatcher(action.getActionUrl(),
                        "ALL".equalsIgnoreCase(httpMethod) ? "" : httpMethod);
                tmpCachedRoleMappingsQueue.add(new RequestActionMapping(actionResult, matcher));
            });
        }
        cachedRoleMappings = tmpCachedRoleMappingsQueue;
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Long startTms = System.currentTimeMillis();
        reload();
        Long endTms = System.currentTimeMillis();
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Action roles mapping loaded in %s ms!", endTms - startTms));
        }
    }
}

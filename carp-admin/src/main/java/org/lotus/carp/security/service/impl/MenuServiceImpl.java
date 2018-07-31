package org.lotus.carp.security.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.commons.collections.CollectionUtils;
import org.lotus.carp.base.service.BaseService;
import org.lotus.carp.security.domain.Menu;
import org.lotus.carp.security.domain.Role;
import org.lotus.carp.security.domain.User;
import org.lotus.carp.security.repository.MenuRepository;
import org.lotus.carp.security.repository.UserRepository;
import org.lotus.carp.security.vo.MenuCreateDto;
import org.lotus.carp.security.vo.MenuUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:52 PM
 */
@Service
public class MenuServiceImpl extends BaseService<MenuRepository, Menu, Integer, MenuCreateDto, MenuUpdateDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Example<Menu> createExampleQuery(String q) {
        Menu menuQuery = new Menu();
        menuQuery.setName(q);
        menuQuery.setUrl(q);
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains());
        return Example.of(menuQuery, matcher);
    }


    @Override
    public Integer getUpdateDtoId(MenuUpdateDto updateDto) {
        return updateDto.getId();
    }

    @Override
    public Menu newOne() {
        return new Menu();
    }

    @Transactional(readOnly = true, rollbackFor = {})
    public List<Menu> getMenusByUserName(String userName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName), "userName should not be null");
        Set<Menu> userMenus = new HashSet<>();
        List<Menu> result = new ArrayList<>();
        User user = userRepository.findByUserName(userName);
        if (null == user) {
            return Collections.emptyList();
        }
        Set<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> {
                Set<Menu> roleMenus = role.getMenus();
                if (CollectionUtils.isNotEmpty(roleMenus)) {
                    roleMenus.forEach(menu -> {
                        userMenus.add(menu);
                        Menu rr = menu;
                        while (rr != null && rr.getParentId() > 0) {
                            Menu parentMenu = menuRepository.getOne(rr.getParentId());
                            if (!userMenus.contains(parentMenu)) {
                                userMenus.add(parentMenu);
                            } else {
                                break;
                            }
                            rr = parentMenu;
                        }
                    });
                }
            });
        }
        result.addAll(userMenus);
        Collections.sort(result, (a, b) -> {
            if (a.getPriority() > b.getPriority()) {
                return 1;
            }
            if (a.getPriority() < b.getPriority()) {
                return -1;
            }
            return 0;
        });
        return result;
    }
}

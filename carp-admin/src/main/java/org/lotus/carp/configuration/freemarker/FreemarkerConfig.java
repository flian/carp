package org.lotus.carp.configuration.freemarker;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.TemplateHashModel;
import org.lotus.carp.base.config.CarpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 4/27/2018
 * Time: 11:30 AM
 */
@Configuration
@AutoConfigureAfter(CarpConfig.class)
public class FreemarkerConfig {
    private static final String preFIx = "";
    private static Logger logger = LoggerFactory.getLogger(FreemarkerConfig.class);

    /**
     * freemarker FreeMarkerConfigurer
     *
     * @param freeMarkerConfigurer
     */
    @Autowired
    public void configFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        //配置freeMarkerConfigurer，如tag等
    }

    /**
     * 配置freemarker若干属性
     *
     * @param resolver
     */
    @Autowired
    public void configureFreemarker(FreeMarkerViewResolver resolver, CarpConfig carpConfig) {
        //设置某些静态方法
        if (null != carpConfig.getExposeStaticClasses()) {
            List<Class> classList = new ArrayList<>();
            Arrays.stream(carpConfig.getExposeStaticClasses()).forEach(className -> {
                try {
                    classList.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    logger.error("找不到class:" + className, e);
                }
            });
            configStaticConst(resolver, classList);
        }

        //设置页面可访问的bean
        if (null != carpConfig.getExposeBeanNames() && carpConfig.getExposeBeanNames().length > 0) {
            resolver.setExposedContextBeanNames(carpConfig.getExposeBeanNames());
        }
    }


    /**
     * 配置freemarker工具。对Freemarker的viewResolver,添加部分静态方法调用功能
     *
     * @param resolver
     */
    private void configStaticConst(FreeMarkerViewResolver resolver, List<Class> defaultStaticClasses) {
        defaultStaticClasses.forEach(clazz -> {
            TemplateHashModel m = getStaticModel(clazz);
            if (null != m) {
                resolver.getAttributesMap().put(String.format("%s%s", preFIx, clazz.getSimpleName()), m);
            }
        });
    }

    private static TemplateHashModel getStaticModel(Class clazz) {
        try {
            BeansWrapper wrapper = new BeansWrapperBuilder(freemarker.template.Configuration.VERSION_2_3_21).build();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(clazz.getName());
            return fileStatics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

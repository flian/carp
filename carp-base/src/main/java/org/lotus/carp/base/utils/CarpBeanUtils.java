package org.lotus.carp.base.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * carp Bean工具栏
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 10:46 AM
 */
@Slf4j
public class CarpBeanUtils {
    /**
     * copy到目标bean
     *
     * @param source
     * @param clzz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T copy(Object source, Class<T> clzz) {
        T object = null;
        try {
            object = clzz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, object);
        } catch (InstantiationException e) {
            log.error("CarpBeanUtils.copy error: ", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            log.error("CarpBeanUtils.copy error: ", e);
            throw new RuntimeException(e);
        }
        return object;
    }
}

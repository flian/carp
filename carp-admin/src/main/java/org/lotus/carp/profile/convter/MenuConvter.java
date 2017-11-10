package org.lotus.carp.profile.convter;

import org.lotus.carp.profile.domain.Menu;
import org.lotus.carp.profile.vo.MenuResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:29 PM
 */
@Component
public class MenuConvter implements Converter<Menu, MenuResult> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public MenuResult convert(Menu source) {
        MenuResult result = new MenuResult();
        BeanUtils.copyProperties(source, result);
        return result;
    }
}

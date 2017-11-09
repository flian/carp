package org.lotus.carp.profile.convter;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.lotus.carp.profile.domain.User;
import org.lotus.carp.profile.vo.UserResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/4/2017
 * Time: 4:24 PM
 */
@Component
public class UserConvter implements Converter<User,UserResult> {
    public UserResult toResult(User source) {
        UserResult result = new UserResult();
        result.setUserName(source.getUserName());
        result.setRoles(Joiner.on(",").join(Iterables.transform(source.getRoles(), r -> r.getCode())));
        return result;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public UserResult convert(User source) {
        return toResult(source);
    }
}

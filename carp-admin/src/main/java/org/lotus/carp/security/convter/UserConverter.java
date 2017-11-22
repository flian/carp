package org.lotus.carp.security.convter;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.lotus.carp.security.domain.User;
import org.lotus.carp.security.vo.UserResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/4/2017
 * Time: 4:24 PM
 */
@Component
public class UserConverter implements Converter<User,UserResult> {
    public UserResult toResult(User source) {
        UserResult result = new UserResult();
        result.setId(source.getId());
        result.setUserName(source.getUserName());
        if(null != source.getRoles()){
            result.setRoles(Joiner.on(",").join(Iterables.transform(source.getRoles(), r -> r.getCode())));
            source.getRoles().forEach( r->result.getRoleCodes().add(r.getCode()));
        }
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

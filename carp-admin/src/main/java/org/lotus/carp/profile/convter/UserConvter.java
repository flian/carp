package org.lotus.carp.profile.convter;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.lotus.carp.profile.domain.User;
import org.lotus.carp.profile.vo.UserResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/4/2017
 * Time: 4:24 PM
 */
@Component
public class UserConvter {
    public UserResult toResult(User source) {
        UserResult result = new UserResult();
        result.setUserName(source.getUserName());
        result.setRoles(Joiner.on(",").join(Iterables.transform(source.getRoles(), r -> r.getCode())));
        return result;
    }

    public List<UserResult> toList(Iterable<User> source) {
        List<UserResult> list = new ArrayList<>();
        source.forEach(u -> list.add(toResult(u)));
        return list;
    }
}

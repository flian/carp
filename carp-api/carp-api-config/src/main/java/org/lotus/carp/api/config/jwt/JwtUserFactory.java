package org.lotus.carp.api.config.jwt;

import org.lotus.carp.customer.domain.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 11:02 AM
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Customer user) {
        return new JwtUser(
                user.getId(),
                user.getUuid(),
                user.getUserName(),
                user.getPassword(),
                mapToGrantedAuthorities(Collections.EMPTY_LIST),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

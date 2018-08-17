package org.lotus.carp.api.config.jwt.impl;

import org.lotus.carp.api.config.jwt.AuthService;
import org.lotus.carp.api.config.jwt.JwtTokenUtil;
import org.lotus.carp.api.config.jwt.JwtUser;
import org.lotus.carp.customer.service.CustomerService;
import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 11:49 AM
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource(name = "jwtUserService")
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public CustomerDetailResult register(CustomerRegisterDto userToAdd) {
        return customerService.register(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String loginWithUuid(String uuid) {
        CustomerDetailResult customerDetailResult = customerService.getByUuid(uuid);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(customerDetailResult.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, jwtTokenUtil.getCreatedDateFromToken(oldToken))) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}

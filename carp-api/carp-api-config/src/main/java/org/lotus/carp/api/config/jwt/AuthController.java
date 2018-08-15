package org.lotus.carp.api.config.jwt;

import org.lotus.carp.api.config.dto.JwtAuthenticationRequest;
import org.lotus.carp.api.config.dto.JwtAuthenticationResponse;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 12:10 PM
 */
@RestController
public class AuthController implements BaseController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseWrapper<JwtAuthenticationResponse> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUserName(), authenticationRequest.getPassword());

        // Return the token
        return response().execSuccess(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseWrapper<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return response().execFailue();
        } else {
            return response().execSuccess(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public ResponseWrapper<CustomerDetailResult> register(@RequestBody CustomerRegisterDto addedUser) throws AuthenticationException {
        return response().execSuccess(authService.register(addedUser));
    }
}

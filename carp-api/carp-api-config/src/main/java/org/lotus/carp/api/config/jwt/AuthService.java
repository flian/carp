package org.lotus.carp.api.config.jwt;

import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 11:48 AM
 */
public interface AuthService {
    CustomerDetailResult register(CustomerRegisterDto userToAdd);
    String loginWithUuid(String uuid);
    String login(String username, String password);
    String refresh(String oldToken);
}

package org.lotus.carp.api.config.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 12:15 PM
 */
@Data
public class JwtAuthenticationRequest {
    private String userName;
    private String password;
}

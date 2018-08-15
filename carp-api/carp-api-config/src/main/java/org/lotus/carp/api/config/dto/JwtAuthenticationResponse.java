package org.lotus.carp.api.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 8/15/2018
 * Time: 12:14 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse  implements Serializable {
    private static final long serialVersionUID = 3557444111016824656L;
    private String token;
}

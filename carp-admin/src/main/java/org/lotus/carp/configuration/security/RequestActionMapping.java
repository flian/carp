package org.lotus.carp.configuration.security;

import lombok.Data;
import org.lotus.carp.security.vo.SecurityActionResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/22/2017
 * Time: 3:53 PM
 */
@Data
public class RequestActionMapping<T> {
    private SecurityActionResult action;
    private T matcher;
    public RequestActionMapping(SecurityActionResult action, T matcher){
        this.action = action;
        this.matcher = matcher;
    }
}

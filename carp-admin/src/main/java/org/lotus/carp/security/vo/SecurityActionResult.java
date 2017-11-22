package org.lotus.carp.security.vo;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/22/2017
 * Time: 4:10 PM
 */
@Data
public class SecurityActionResult {
    private Integer id;
    private String name;
    private String actionUrl;
    private String actionMethod;
    private Integer priority;
    private boolean leaf;
    private transient Collection<ConfigAttribute> grantedRoles = new ArrayList<>();

}

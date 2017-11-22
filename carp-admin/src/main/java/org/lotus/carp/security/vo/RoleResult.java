package org.lotus.carp.security.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/9/2017
 * Time: 5:14 PM
 */
@Data
public class RoleResult {
    private Long id;
    private String code;
    private String name;
    private String users;
}

package org.lotus.carp.profile.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/16/2017
 * Time: 4:38 PM
 */
@Data
public class UserRoleUpdateDto {
    private Long userId;
    private List<String> roles;
}

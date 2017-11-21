package org.lotus.carp.profile.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/21/2017
 * Time: 5:18 PM
 */
@Data
public class RoleCreateDto {
    @NotNull
    @Length(min = 6, max = 20)
    private String code;
    @NotNull
    @Length(min = 1, max = 50)
    private String name;
}

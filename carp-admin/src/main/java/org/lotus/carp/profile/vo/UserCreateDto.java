package org.lotus.carp.profile.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/21/2017
 * Time: 4:44 PM
 */
@Data
public class UserCreateDto {
    @NotNull
    @Length(min=3,max=20)
    private String name;
    @NotNull
    @Length(min=6,max=20)
    private String password;
}

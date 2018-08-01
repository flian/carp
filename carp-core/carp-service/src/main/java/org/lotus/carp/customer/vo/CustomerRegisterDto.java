package org.lotus.carp.customer.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 用户注册dto
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 10:44 AM
 */
@Getter
@Setter
public class CustomerRegisterDto {
    @NotNull
    private String userName;
    @NotNull
    @Length(min = 6)
    private String password;
    @NotNull
    private String mobile;
    @NotNull
    private String nickName;
}

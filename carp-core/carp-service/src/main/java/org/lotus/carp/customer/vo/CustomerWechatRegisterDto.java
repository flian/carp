package org.lotus.carp.customer.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.lotus.carp.customer.enums.Gender;

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
public class CustomerWechatRegisterDto {
    @NotNull
    private String nickName;

    private String openId;

    private String unionId;

    private Gender gender;

    private String headIconUrl;
}

package org.lotus.carp.customer.vo;

import lombok.Getter;
import lombok.Setter;
import org.lotus.carp.customer.enums.Gender;

/**
 * 用户详情
 *
 * @author: Foy Lian
 * Date: 8/1/2018
 * Time: 10:44 AM
 */
@Getter
@Setter
public class CustomerDetailResult {
    private Long id;
    private String uuid;
    private String userName;
    private String mobile;
    private String nickName;
    private Gender gender;
    private String avatar;
}

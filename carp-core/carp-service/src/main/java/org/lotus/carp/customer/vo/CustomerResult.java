package org.lotus.carp.customer.vo;

import lombok.Getter;
import lombok.Setter;
import org.lotus.carp.customer.enums.Gender;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:19 PM
 */
public class CustomerResult {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String uuid;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String nickName;

    @Getter
    @Setter
    private Gender gender;

    @Getter
    @Setter
    private String avatar;
}

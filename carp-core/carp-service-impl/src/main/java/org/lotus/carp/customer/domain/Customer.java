package org.lotus.carp.customer.domain;

import lombok.Getter;
import lombok.Setter;
import org.lotus.carp.customer.enums.From;
import org.lotus.carp.customer.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

/**
 * commerce 顾客实体
 *
 * @author: Foy Lian
 * Date: 11/30/2017
 * Time: 11:15 AM
 */
@Entity
@Table(name = "carp_customer")
public class Customer implements Serializable {

    public static final String DEFAULT_MOBILE_NO = "00000000000";

    private static final long serialVersionUID = -3591585058862838112L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 64)
    private String uuid;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 20)
    private String userName;

    @Getter
    @Setter
    @Column(nullable = false, length = 64)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false, length = 11)
    private String mobile;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String nickName;

    @Getter
    @Setter
    @Column
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Getter
    @Setter
    @Column(length = 64)
    private String avatar;

    /**
     * 最近一次重设置密码时间
     */
    @Getter
    @Setter
    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;

    /**
     * 从什么渠道注册的用户
     */
    @Getter
    @Setter
    @Column(name = "from_channel")
    @Enumerated(STRING)
    private From fromChannel;

    /**
     * 微信openId
     */
    @Getter
    @Setter
    @Column(name = "wx_open_id")
    private String openId;

    /**
     * 微信unionId
     */
    @Getter
    @Setter
    @Column(name = "wx_union_id")
    private String unionId;
}

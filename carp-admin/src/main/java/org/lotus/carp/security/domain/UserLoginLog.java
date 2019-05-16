package org.lotus.carp.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志
 *
 * @author: Foy Lian
 * Date: 5/16/2019
 * Time: 8:21 AM
 */
@Entity
@Table(name = "carp_user_login_log")
public class UserLoginLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Column(name = "userName")
    private String userName;
    @Getter
    @Setter
    @Column(name = "agent")
    private String agent;
    @Getter
    @Setter
    @Column(name = "ip")
    private String ip;
    @Getter
    @Setter
    @Column(name = "log")
    private String log;

    @Getter
    @Setter
    @Column(name = "created_datetime")
    private Date createdDatetime;
}

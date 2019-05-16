package org.lotus.carp.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *  账户信息
 * @author : Foy Lian
 * Date: 8/3/2017
 * Time: 5:53 PM
 */

@Entity
@Table(name = "carp_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6115835806786604007L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    /**
     * 账户名
     */
    @Getter
    @Setter
    @Column(name="user_name",unique = true, nullable = false,length = 20)
    private String userName;

    @Getter
    @Setter
    @Column(nullable = false,length = 64)
    private String password;

    /**
     * 账户是否可用
     */
    @Getter
    @Setter
    @Column(name="enable",nullable = false)
    private Boolean enable;

    /**
     * 账户角色
     */
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "carp_user_role",
            joinColumns = {@JoinColumn(name = "user_name", referencedColumnName = "user_name")},
            inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "role_code"))
    private Set<Role> roles;
}

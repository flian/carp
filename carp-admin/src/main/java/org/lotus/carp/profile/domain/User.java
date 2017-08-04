package org.lotus.carp.profile.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/3/2017
 * Time: 5:53 PM
 */
@Data
@Entity
@Table(name = "carp_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6115835806786604007L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="user_name")
    private String userName;
    private String password;
    @Transient
    private String salt;
    @ManyToMany
    @JoinTable(name = "carp_user_role",
            joinColumns = {@JoinColumn(name = "user_name", referencedColumnName = "user_name")},
            inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "role_code"))
    private Set<Role> roles;
}

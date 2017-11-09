package org.lotus.carp.profile.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
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

    @Getter
    @Setter
    @Column(name="user_name",unique = true,length = 20)
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "carp_user_role",
            joinColumns = {@JoinColumn(name = "user_name", referencedColumnName = "user_name")},
            inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "role_code"))
    private Set<Role> roles;
}

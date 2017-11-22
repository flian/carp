package org.lotus.carp.security.domain;

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
@Table(name = "carp_role")
public class Role implements Serializable{
    private static final long serialVersionUID = -8240641889345950877L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "role_code",unique = true,length = 20)
    private String code;

    @Getter
    @Setter
    @Column(name = "role_name",length = 50)
    private String name;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "carp_role_menu",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
    private Set<Menu> menus;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "carp_role_action",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id"))
    private Set<Action> actions;

}

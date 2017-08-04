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
@Table(name = "carp_role")
public class Role implements Serializable{
    private static final long serialVersionUID = -8240641889345950877L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role_code")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

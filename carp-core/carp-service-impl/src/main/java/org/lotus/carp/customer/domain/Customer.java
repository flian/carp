package org.lotus.carp.customer.domain;

import lombok.Getter;
import lombok.Setter;
import org.lotus.carp.customer.enums.Gender;


import javax.persistence.*;
import java.io.Serializable;

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

}

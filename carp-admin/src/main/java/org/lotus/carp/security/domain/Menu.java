package org.lotus.carp.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  权限菜单
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:09 PM
 */
@Entity
@Table(name = "carp_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -185507695344453153L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name="parent_id",columnDefinition ="int default -1")
    private Integer parentId;

    @Getter
    @Setter
    @Column(length = 20, nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(length = 100,nullable = false)
    private String url;

    @Getter
    @Setter
    @Column(columnDefinition ="int default 100")
    private Integer priority;

    @Getter
    @Setter
    private boolean leaf;

}

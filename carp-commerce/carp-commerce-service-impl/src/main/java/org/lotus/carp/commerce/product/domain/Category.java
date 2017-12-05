package org.lotus.carp.commerce.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 2:47 PM
 */
@Entity
@Table(name = "carp_category")
public class Category implements Serializable {
    private static final long serialVersionUID = 3409480919905627820L;
    @Id
    @Getter@Setter
    private Integer id;

    @Column(name = "name",nullable = false,length = 20)
    @Getter@Setter
    private String name;

    @Column(name = "description",length = 255)
    @Getter@Setter
    private String description;

    @Column(name = "parent_category_id")
    @Getter@Setter
    private Integer parentCategoryId;
}

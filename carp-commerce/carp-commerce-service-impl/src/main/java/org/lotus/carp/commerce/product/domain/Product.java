package org.lotus.carp.commerce.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 产品实体
 *
 * @author: Foy Lian
 * Date: 11/30/2017
 * Time: 11:15 AM
 */
@Entity
@Table(name = "carp_product")
public class Product implements Serializable {

    private static final long serialVersionUID = -4967455755473957767L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "parent_category_id")
    @Getter
    @Setter
    private Integer parentCategoryId;

    @Getter
    @Setter
    @Column(nullable = false, length = 64)
    private String name;

    @Getter
    @Setter
    @Column
    private String shortDescription;

    @Getter
    @Setter
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longtext")
    private String longDescription;

    @Getter
    @Setter
    @OneToMany(mappedBy = "product",orphanRemoval=true)
    private Set<Sku> sku;
}

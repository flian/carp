package org.lotus.carp.commerce.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 10:52 AM
 */
@Entity
@Table(name = "carp_sku")
public class Sku implements Serializable {
    private static final long serialVersionUID = 5184435365046964459L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(length = 64)
    private String skuName;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 建议售价
     */
    @Getter
    @Setter
    @Column(precision = 12, scale = 2)
    private BigDecimal listPrice;

    /**
     * 是否打折出售
     */
    @Getter
    @Setter
    @Column
    private Boolean onSale = false;

    /**
     * 打折售价
     */
    @Getter
    @Setter
    @Column(precision = 12, scale = 2)
    private BigDecimal salePrice;

    /**
     *  实际剩余库存量
     */
    @Getter
    @Setter
    @Column
    private Integer stockLevel;

    /**
     * 锁定库存量
     */
    @Getter
    @Setter
    @Column
    private Integer stockLocked;

    /**
     * 安全库存量
     * 当 剩余库存 - 锁定库存 < 安全库存时，不允许下单
     */
    @Getter
    @Setter
    @Column
    private Integer stockSafeLevel;
}

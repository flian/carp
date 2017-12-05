package org.lotus.carp.commerce.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  订单项实体
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 11:21 AM
 */
@Entity
@Table(name="carp_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 4781451216569054264L;

    @Id
    @Column(name = "order_item_id")
    @Getter @Setter
    private Long orderItemId;

    @Column(name = "order_id",insertable = false,updatable = false)
    @Getter
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @Getter@Setter
    private Order order;

    @Column(name = "product_id",nullable = false)
    @Getter@Setter
    private Long productId;

    @Column(name = "product_name",nullable = false,length = 64)
    @Getter@Setter
    private String productName;

    @Column(name = "sku_id")
    @Getter@Setter
    private Long skuId;

    @Column(name = "sku_name",length = 64)
    @Getter@Setter
    private String skuName;

    @Column(name = "quantity",nullable = false)
    @Getter@Setter
    private int quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal rawPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal price;

    @Column(nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal rawSubtotal;

    @Column(nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal subtotal;
}

package org.lotus.carp.commerce.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  订单实体
 *
 * @author: Foy Lian
 * Date: 11/30/2017
 * Time: 11:16 AM
 */
@Entity
@Table(name = "carp_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 6954539864231257186L;

    @Id
    @Column(name = "order_id")
    @Getter@Setter
    private Long orderId;

    @Column(name = "customer_id")
    @Getter@Setter
    private Long customerId;

    @Column(name = "customer_name",length = 20)
    @Getter@Setter
    private String customerName;

    @OneToMany(mappedBy = "order",orphanRemoval=true)
    @Getter@Setter
    private List<OrderItem> items;

    /**
     * item原总价
     */
    @Column(name = "raw_subtotal",nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal rawSubtotal;
    /**
     * item总价
     */
    @Column(name = "subtotal",nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal subtotal;
    /**
     * 订单原总价
     */
    @Column(name = "raw_total",nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal rawTotal;
    /**
     * 订单总价
     */
    @Column(name = "total",nullable = false, precision = 12, scale = 2)
    @Getter@Setter
    private BigDecimal total;
}

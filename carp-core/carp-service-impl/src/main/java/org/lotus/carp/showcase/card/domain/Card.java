package org.lotus.carp.showcase.card.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 7/31/2017
 * Time: 3:12 PM
 */
@Entity
@Data
@Table(name = "carp_showcase_card")
public class Card implements Serializable {

    private static final long serialVersionUID = 3909450513871676563L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 卡号
     */
    @Column(nullable = false, unique = true, length = 64)
    private String cardId;

    /**
     * 发行面值
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal issueValue;

    /**
     * 冻结金额
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal frozenValue = BigDecimal.ZERO;

    /**
     *  当前余额
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balanceValue;
}

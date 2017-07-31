package org.lotus.carp.showcase.card.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 3:12 PM
 */
@Entity
@Data
public class Card implements Serializable {

    private static final long serialVersionUID = 3909450513871676563L;
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String cardId;
    @Column
    private BigDecimal issueValue;
    @Column
    private BigDecimal frozenValue;
    @Column
    private BigDecimal balanceValue;
}

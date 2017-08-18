package org.lotus.carp.showcase.card.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 2:26 PM
 */
@Data
public class CardResult {
    private Long id;
    private String cardId;
    private BigDecimal balanceValue;
    private BigDecimal frozenValue;
    private BigDecimal issueValue;
}

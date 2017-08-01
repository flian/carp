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
public class CardDto {
    private Long id;
    private BigDecimal cardId;
    private BigDecimal issueValue;
}

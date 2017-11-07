package org.lotus.carp.showcase.card.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Foy Lian
 *         Date: 7/31/2017
 *         Time: 2:26 PM
 */
@Data
public class CardUpdateDto {

    @NotNull(message = "卡号不能为空")
    @Length(max = 64, message = "卡号不能超过64位")
    private String  cardId;

    @NotNull(message = "冻结金额不能为空")
    private BigDecimal frozenValue;

    @NotNull(message = "余额不能为空")
    private BigDecimal balanceValue;
}

package org.lotus.carp.showcase.card.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class CardCreateDto {
    @NotNull
    @Min(value = 1, message = "卡面值最少为1")
    @Max(value = 2000, message = "卡面值最多2w")
    private BigDecimal issueValue;

    public BigDecimal getIssueValue() {
        return issueValue;
    }

    public void setIssueValue(BigDecimal issueValue) {
        this.issueValue = issueValue;
    }
}

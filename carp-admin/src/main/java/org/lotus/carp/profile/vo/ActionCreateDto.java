package org.lotus.carp.profile.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/15/2017
 * Time: 5:50 PM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionCreateDto {
    private Integer parentId;
    @NotNull(message = "")
    @Length(min = 1, max = 20, message = "名称不能超过20个汉字")
    private String name;
    @Length(max = 100, message = "actionUrl不能超过100个汉字")
    private String actionUrl;
    @Length(min = 1, max = 5, message = "actionMethod不能超过5")
    private String actionMethod;
    private Integer priority;
    private boolean leaf;
}

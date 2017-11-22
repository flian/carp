package org.lotus.carp.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/15/2017
 * Time: 2:55 PM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuUpdateDto {
    private Integer id;
    @NotNull(message = "菜单名称为必填字段")
    @Length(min = 1, max = 20, message = "名称长度1-20个汉字")
    private String name;
    @Length(max = 100, message = "url不能超过100字符")
    private String url;
    private Integer priority;
    private boolean leaf;
}

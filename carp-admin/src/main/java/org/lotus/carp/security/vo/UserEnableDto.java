package org.lotus.carp.security.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 5/16/2019
 * Time: 8:49 AM
 */
@Data
public class UserEnableDto {
    @NotNull
    private Boolean enable;
}

package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * carp日期格式
 *
 * @author: Foy Lian
 * Date: 2/21/2019
 * Time: 11:03 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
public @interface CarpJsonDateTime {
}

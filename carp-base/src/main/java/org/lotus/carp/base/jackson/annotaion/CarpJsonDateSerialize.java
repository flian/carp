package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.lotus.carp.base.jackson.DateJsonSerializer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * carp序列化json 日期格式yyyy-MM-dd
 *
 * @author: Foy Lian
 * Date: 7/4/2018
 * Time: 10:40 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(using=DateJsonSerializer.class)
public @interface CarpJsonDateSerialize {
}

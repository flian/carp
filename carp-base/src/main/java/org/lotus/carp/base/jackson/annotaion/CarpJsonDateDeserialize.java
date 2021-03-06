package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.lotus.carp.base.jackson.DateJsonDeserializer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * data 格式反序列化，支持格式:
 * yyyy-MM-dd
 * yyyy-MM-dd HH:mm:ss
 *
 * @author: Foy Lian
 * Date: 9/3/2018
 * Time: 3:03 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = DateJsonDeserializer.class)
public @interface CarpJsonDateDeserialize {
}

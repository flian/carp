package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.lotus.carp.base.jackson.DateTimeJsonSerializer;

/**
 * carp序列化json 日期时间格式yyyy-MM-dd HH:mm:ss
 *
 * @author: Foy Lian
 * Date: 7/4/2018
 * Time: 10:40 AM
 */
@JsonSerialize(using = DateTimeJsonSerializer.class)
public @interface CarpJsonDateTimeSerialize {
}

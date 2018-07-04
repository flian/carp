package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.lotus.carp.base.jackson.DateJsonSerializer;

/**
 * carp序列化json 日期
 *
 * @author: Foy Lian
 * Date: 7/4/2018
 * Time: 10:40 AM
 */

@JsonSerialize(using=DateJsonSerializer.class)
public @interface CarpJsonDateSerialize {
}

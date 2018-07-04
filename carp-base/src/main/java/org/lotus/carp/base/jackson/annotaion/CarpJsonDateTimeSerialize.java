package org.lotus.carp.base.jackson.annotaion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.lotus.carp.base.jackson.DateTimeJsonSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 7/4/2018
 * Time: 10:40 AM
 */
@JsonSerialize(using = DateTimeJsonSerializer.class)
public @interface CarpJsonDateTimeSerialize {
}

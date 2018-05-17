package org.lotus.carp.base.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Foy Lian
 * @since 2017/9/5
 * 描述：前端API对外输出JSON时，处理详细日期时间格式化(yyyy-MM-dd)
 */
public class DateTimeJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat(DateJsonDeserializer.DEFAULT_DATE_TIME_PATTERN);
        jsonGenerator.writeString(format.format(date));
    }
}

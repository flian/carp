package org.lotus.carp.base.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Foy Lian
 * @since 2017/9/5
 * 描述： API接口时，解析前端json的日期时间格式.
 *  日期格式： yyyy-MM-dd
 *  日期时间格式：yyyy-MM-dd HH:mm:ss
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final Logger logger = LoggerFactory.getLogger(DateJsonDeserializer.class);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        try {
            return (parseDate(jsonParser.getText()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static Date parseDate(String source) {
        if (Strings.isNullOrEmpty(source)) {
            return null;
        }
        String pattern = source.length() == 10 ? DEFAULT_DATE_PATTERN : DEFAULT_DATE_TIME_PATTERN;
        //SimpleDateFormat不是线程安全的，所以需要每次new出来
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            logger.error("转换日期、时间出错." + e);
        }
        return null;
    }
}

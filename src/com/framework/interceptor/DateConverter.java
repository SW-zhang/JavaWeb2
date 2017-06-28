package com.framework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换器
 * 供MVC的Controller传递参数时处理对时间类型属性的转换
 */
public class DateConverter implements Converter<String, Date> {
    private static final Logger log = LoggerFactory.getLogger(DateConverter.class);

    public Date convert(String source) {
        if (StringUtils.hasText(source)) {
            try {
                // 毫秒
                if (source.matches("^\\d{13}$")) {
                    return new Date(Long.parseLong(source));
                }
                // 秒
                if (source.matches("^\\d{10}$")) {
                    source += "000";
                    return new Date(Long.parseLong(source));
                }
                // yyyy-MM-dd HH:mm:ss
                if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                    SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return datetimeFormat.parse(source);
                }
                // yyyy-MM-dd
                if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.parse(source);
                }
                log.info(String.format("could not parse date, date format is error {%s}", source));
                throw new IllegalArgumentException("Could not parse date, date format is error ");
            } catch (ParseException ex) {
                IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
                iae.initCause(ex);
                throw iae;
            }
        } else {
            return null;
        }
    }

}

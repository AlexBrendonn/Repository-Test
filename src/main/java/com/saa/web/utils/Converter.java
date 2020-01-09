package com.saa.web.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static class DateUtils{
        public static ZonedDateTime fromString(String value){
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
            return  ZonedDateTime.parse(value);
        }

        public static String fromLocalDateTime(ZonedDateTime date){
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        }
    }
}

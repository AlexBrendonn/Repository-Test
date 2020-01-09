package com.saa.web.utils;

public class Replaces {
    public static String replaceData64 (String base64) throws Exception {

        try {
            String[] array = base64.split(";base64,");

            if (array.length < 2) throw new Exception("Formato base 64 invalido");

            return array[1];

        }catch (Exception ex){
            throw ex;
        }

    }
}

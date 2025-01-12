package com.nerugdev.literaturaApp.util;

public class StringUtil {

    public static String normalize(String text) {
        return text
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");
    }
}
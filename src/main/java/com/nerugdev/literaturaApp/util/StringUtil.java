package com.nerugdev.literaturaApp.util;

import java.text.Normalizer;

public class StringUtil {
    public static String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
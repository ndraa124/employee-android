package com.id22.employees.core.utils;

public class Helper {
    public static String removePrefix(String s, String prefix) {
        if (s != null && prefix != null && s.startsWith(prefix)) {
            return s.substring(prefix.length());
        }
        return s;
    }

    public static String removeSuffix(final String s, final String suffix) {
        if (s != null && suffix != null && s.endsWith(suffix)) {
            return s.substring(0, s.length() - suffix.length());
        }
        return s;
    }
}

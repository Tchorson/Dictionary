package com.tchorek.dictionary.enums;

public enum Language {
    POLISH("pl"),
    ENGLISH("eng"),
    GERMAN("ger"),
    SPANISH("spa"),
    UNKNOWN("unknown");

    Language(String language) {
    }

    public static Language of(String value) {
        try {
            return Language.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

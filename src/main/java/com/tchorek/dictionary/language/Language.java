package com.tchorek.dictionary.language;

import java.util.Arrays;

public enum Language {
    POLISH("pl"),
    ENGLISH("eng"),
    GERMAN("ger"),
    SPANISH("spa"),
    UNKNOWN("unknown");

    private String language;

    Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public static Language of(String value) {
        return Arrays.stream(Language.values())
                .filter(lan -> lan.getLanguage().equals(value))
                .findFirst().orElse(UNKNOWN);
    }
}

/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.language;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanguageConverterTest {

    private final LanguageConverter converter = new LanguageConverter();

    private final String word = "eng";
    private final String unknownLanguage = "unknown";

    private final Language language = Language.ENGLISH;
    private final Language unknown = Language.UNKNOWN;

    @Test
    void shouldConvertAttributeToColumn() {
        String result = converter.convertToDatabaseColumn(language);

        assertEquals(result, word);
    }

    @Test
    void shouldConvertColumnToAttribute() {
        Language result = converter.convertToEntityAttribute(word);

        assertEquals(result, language);
    }

    @Test
    void shouldSetLanguageToUnknown() {
        Language result = converter.convertToEntityAttribute(unknownLanguage);

        assertEquals(result, unknown);
    }

    @Test
    void shouldSetWordToUnknown() {
        String result = converter.convertToDatabaseColumn(unknown);

        assertEquals(result, unknownLanguage);
    }
}
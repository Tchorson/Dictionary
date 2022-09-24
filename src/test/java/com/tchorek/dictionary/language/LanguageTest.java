/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.language;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageTest {

    private Language language;
    private String german = "ger";
    private String english = Language.ENGLISH.name();

    private String unknown = "unknown";

    @Test
    public void ShouldAssignGermanValue(){
        //given
        language = Language.GERMAN;
        //when
        String result = language.getLanguage();
        //then
        assertEquals(german, result);
    }

    @Test
    public void ShouldAssignEnglishValue(){
        //given
        language = Language.of("eng");
        //when
        String result = language.name();
        //then
        assertEquals(english, result);
    }

    @Test
    public void ShouldAssignDefaultValue(){
        //given
        language = Language.of("BLABLA");
        //when
        String result = language.getLanguage();
        //then
        assertEquals(unknown, result);
    }
}

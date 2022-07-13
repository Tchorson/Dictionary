package com.tchorek.dictionary.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageTest {

    private Language language;
    private String german = "ger";
    private String unknown = "UNKNOWN";

    @Test
    public void ShouldAssignValue(){
        //given
        language = Language.GERMAN;
        //when
        String result = language.name();
        //then
        assertEquals(german, result);
    }

    @Test
    public void ShouldAssignDefaultValue(){
        //given
        language = Language.of("BLABLA");
        //when
        String result = language.name();
        //then
        assertEquals(result, unknown);
    }
}

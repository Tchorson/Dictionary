/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import com.tchorek.dictionary.language.Language;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RecordMapperTest {

    List<RecordEntity> list;
    private final String word = "word";
    private final String translation = "słowo";
    private final Language language = Language.ENGLISH;
    private final String user = "Tchorson";

    @Test
    public void shouldMapEntityToModel() {
        //given
        list = Collections.singletonList(new RecordEntity(1, word, translation, language, user));
        //when
        List<RecordModel> results = RecordMapper.mapToModels(list);
        RecordModel result = results.get(0);
        //then
        assertEquals(1, results.size());
        assertEquals(word, result.getWord());
        assertEquals(translation, result.getTranslation());
        assertEquals(language, result.getLanguage());
        assertEquals(user, result.getUser());
    }

    @Test
    public void shouldMapEmptyEntityToModel() {
        //given
        list = Collections.singletonList(new RecordEntity(null, null, null, null, null));
        //when
        List<RecordModel> results = RecordMapper.mapToModels(list);
        RecordModel result = results.get(0);
        //then
        assertEquals(1, results.size());
        assertNull(result.getWord());
        assertNull(result.getTranslation());
        assertNull(result.getLanguage());
        assertNull(result.getUser());
    }
}

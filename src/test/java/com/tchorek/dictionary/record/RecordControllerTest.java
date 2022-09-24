/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import com.google.gson.Gson;
import com.tchorek.dictionary.language.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @Autowired
    private MockMvc recordControllerMock;

    @MockBean
    private RecordService service;

    private final int id = 1;
    private final String word = "Dog";
    private final String translation = "Pies";
    private final String newTranslation = "Policjant";
    private final String user = "Mr_Test";
    private final Language english = Language.ENGLISH;
    private final String englishVal = Language.ENGLISH.getLanguage();
    private final Gson gson = new Gson();
    private final RecordModel singleRecordModel = new RecordModel(word, translation, english, user);
    private final RecordModel updatedRecordModel = new RecordModel(word, newTranslation, english, user);

    private final RecordEntity singleRecordEntity = new RecordEntity(id, word, translation, english, user);

    private final String singleModelJson = gson.toJson(singleRecordModel);
    private final String singleEntityJson = gson.toJson(singleRecordEntity);
    private final String updatedModelJson = gson.toJson(updatedRecordModel);

    @Test
    public void shouldReturnRecordsByLanguage() throws Exception {
        when(service.getRecords(user, englishVal)).thenReturn(Collections.singletonList(singleRecordModel));

        this.recordControllerMock.perform(
                        get("/dictionary/list")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("user", user)
                                .queryParam("language",englishVal)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(singleModelJson)));
    }

    @Test
    public void shouldReturnRecordsByWord() throws Exception {
        when(service.getRecord(word, user)).thenReturn(Collections.singletonList(singleRecordEntity));
        String url = "/dictionary/" + user + "/list/" + word;

        this.recordControllerMock.perform(
                        get(url)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(singleEntityJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(singleEntityJson)));
    }

    @Test
    public void shouldSaveRecord() throws Exception {
        this.recordControllerMock.perform(
                        post("/dictionary/request")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(singleEntityJson)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateRecord() throws Exception {
        this.recordControllerMock.perform(
                        put("/dictionary/update")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(updatedModelJson)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteRecord() throws Exception {
        this.recordControllerMock.perform(
                        delete("/dictionary/delete")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(singleEntityJson)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}

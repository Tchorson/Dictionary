/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import com.google.gson.Gson;
import com.tchorek.dictionary.language.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordHttpRequestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final Gson gson = new Gson();
    private final int id = 1;
    private final String word = "Dog";
    private final String translation = "Pies";
    private final String user = "Mr_Test";
    private final Language language = Language.ENGLISH;
    private final RecordEntity recordEntity = new RecordEntity(id, word, translation, language, user);
    private final RecordModel recordModel = new RecordModel(word, translation, language, user);
    HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    private void prepareTestTools() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void shouldExecuteGetRecordsRequest() {
        String url = "http://localhost:" + port + "/dictionary/list";
        String fullUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("user", user)
                .queryParam("language", language.getLanguage())
                .toUriString();

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response =
                restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldExecuteGetRecordRequest() {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("user", user);
        urlParams.put("word", word);

        String url = "http://localhost:" + port + "/dictionary/{user}/list/{word}";
        String fullUrl = UriComponentsBuilder
                .fromHttpUrl(url)
                .buildAndExpand(urlParams)
                .toUriString();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldExecutePostRequest() {
        String url = "http://localhost:" + port + "/dictionary/request";
        String json = gson.toJson(recordEntity);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<RecordEntity> response =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<RecordEntity>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordEntity, response.getBody());
    }

    @Test
    void shouldExecutePutRequest() {
        String url = "http://localhost:" + port + "/dictionary/update";
        String json = gson.toJson(recordModel);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<RecordModel> response =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<RecordModel>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordModel, response.getBody());
    }

    @Test
    void shouldExecuteDeleteRequest() {
        String url = "http://localhost:" + port + "/dictionary/delete";
        String json = gson.toJson(recordModel);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<RecordModel> response =
                restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<RecordModel>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordModel, response.getBody());
    }
}

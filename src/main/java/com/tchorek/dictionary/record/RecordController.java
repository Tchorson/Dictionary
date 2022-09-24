/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "dictionary/")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping(path = "list", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    List<RecordModel> getRecords(@RequestParam("user") String user, @RequestParam("language") String language) {
        return recordService.getRecords(user, language);
    }

    @GetMapping(path = "{user}/list/{word}", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    List<RecordEntity> getRecord(@PathVariable String user, @PathVariable String word) {
        return recordService.getRecord(word, user);
    }

    @PostMapping(path = "request", consumes = "application/json", produces = "application/json")
    public ResponseEntity saveRecord(@RequestBody RecordEntity record) {
        recordService.saveRecord(record);
        return ResponseEntity.ok(record);
    }

    @PutMapping(path = "update", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateRecord(@RequestBody RecordModel record) {
        recordService.updateRecord(record.getWord(), record.getTranslation(), record.getUser());
        return ResponseEntity.ok(record);
    }

    @DeleteMapping(path = "delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity deleteRecord(@RequestBody RecordModel record) {
        recordService.deleteRecord(record.getWord(), record.getTranslation(), record.getUser());
        return ResponseEntity.ok(record);
    }
}

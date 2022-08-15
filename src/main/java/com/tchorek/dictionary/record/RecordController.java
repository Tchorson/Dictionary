package com.tchorek.dictionary.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "dictionary/")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping(path = "list", produces = "application/json")
    public @ResponseBody List<RecordModel> getRecords(@RequestBody RecordModel body) {
        return recordService.getUserRecordsByLanguage(body.getUser(), body.getLanguage());
    }

    @GetMapping(path = "{user}/list/{word}", produces = "application/json")
    public @ResponseBody List<RecordEntity> getRecord(@PathVariable String user, @PathVariable String word) {
        return recordService.getRecordsByWord(word, user);
    }

    @PostMapping("request")
    public ResponseEntity saveRecord(@RequestBody RecordEntity record) {
        recordService.saveRecord(record);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity updateRecord(@RequestBody RecordModel record) {
        recordService.updateRecord(record.getWord(), record.getTranslation(), record.getUser());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteRecord(@RequestBody RecordModel record) {
        recordService.deleteRecord(record.getWord(), record.getTranslation(), record.getUser());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

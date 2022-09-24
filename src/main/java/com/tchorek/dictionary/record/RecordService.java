/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordEntity saveRecord(RecordEntity record){
       return recordRepository.save(record);
    }

    public List<RecordEntity> getRecord(String word, String user){
        return recordRepository.getWord(word, user);
    }

    public List<RecordModel> getRecords(String user, String language){
        return RecordMapper.mapToModels(recordRepository.getWords(user, language));
    }

    @Transactional
    public void updateRecord(String word, String newTranslation, String user){
        recordRepository.updateRecord(word, newTranslation, user);
    }

    @Transactional
    public void deleteRecord(String word, String translation, String user){
        recordRepository.deleteWord(word, translation, user);
    }
}

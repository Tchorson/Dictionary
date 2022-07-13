package com.tchorek.dictionary.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordEntity saveRecord(RecordEntity record){
       return recordRepository.save(record);
    }

    public List<RecordEntity> getRecordsByWord(String word, String user){
        return recordRepository.findWordByUser(word, user);
    }

    public List<RecordModel> getUserRecords(RecordModel requestBody){
        return RecordMapper.mapRecordEntitiesToModels(recordRepository.getUserRecords(requestBody.getUser(), requestBody.getLanguage().name()));
    }

    public void updateRecord(String word, String newTranslation, String user){
        recordRepository.updateRecord(word, newTranslation, user);
    }

    public void deleteRecord(String word, String translation, String user){
        recordRepository.deleteRecord(word, translation, user);
    }
}

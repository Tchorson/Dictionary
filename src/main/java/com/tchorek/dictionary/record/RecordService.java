package com.tchorek.dictionary.record;

import com.tchorek.dictionary.enums.Language;
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

    public List<RecordModel> getUserRecordsByLanguage(String user, Language language){
        return RecordMapper.mapRecordEntitiesToModels(recordRepository.getUserRecordsByLanguage(user, language.name()));
    }

    public void updateRecord(String word, String newTranslation, String user){
        recordRepository.updateRecord(word, newTranslation, user);
    }

    public void deleteRecord(String word, String translation, String user){
        recordRepository.deleteRecord(word, translation, user);
    }
}

package com.tchorek.dictionary.record;

import com.tchorek.dictionary.enums.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {

    private RecordRepository recordRepository;
    private RecordService recordService;
    private RecordEntity firstRecord, secondRecord;
    private List<RecordEntity> recordList;
    private String user, firstWord, firstTranslation, secondWord, secondTranslation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveRecord(){
        //given
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        firstRecord = new RecordEntity(0,null,null,null,"test");
        //when
        when(recordRepository.save(firstRecord)).thenReturn(firstRecord);
        RecordEntity newRecord = recordService.saveRecord(firstRecord);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).save(firstRecord);
        assertThat(newRecord.getUser()).isSameAs(firstRecord.getUser());
    }

    @Test
    public void shouldGetRecord(){
        //given
        firstWord = "Dog";
        firstTranslation = "Pies";
        user = "Mateusz_Tchorek";
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        firstRecord = new RecordEntity(5, firstWord, firstTranslation, Language.ENGLISH, user);
        recordList = Collections.singletonList(firstRecord);
        //when
        when(recordRepository.findWordByUser(firstWord, user)).thenReturn(recordList);
        List<RecordEntity> newRecordList = recordService.getRecordsByWord(firstWord, user);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).findWordByUser(firstWord, user);
        assertThat(newRecordList.size()).isEqualTo(1);
        assertThat(newRecordList.get(0)).isSameAs(firstRecord);
    }

    @Test
    public void shouldGetRecords(){
        //given
        firstWord = "Dog";
        firstTranslation = "Pies";
        secondWord = "Cat";
        secondTranslation = "Kot";
        user = "Mateusz_Tchorek";
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        firstRecord = new RecordEntity(5, firstWord,firstTranslation, Language.ENGLISH, user);
        secondRecord = new RecordEntity(6, secondWord, secondTranslation, Language.ENGLISH, user);

        recordList = Arrays.asList(firstRecord, secondRecord);
        //when
        when(recordRepository.getUserRecordsByLanguage(user, Language.ENGLISH.name())).thenReturn(recordList);
        List<RecordModel> newRecordList = recordService.getUserRecordsByLanguage(user, Language.ENGLISH);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).getUserRecordsByLanguage(user, Language.ENGLISH.name());
        assertThat(newRecordList.size()).isEqualTo(2);
        RecordModel firstNewModel = newRecordList.get(0);
        RecordModel secondNewModel = newRecordList.get(1);

        assertThat(firstNewModel.getUser()).isSameAs(firstRecord.getUser());
        assertThat(firstNewModel.getLanguage()).isSameAs(firstRecord.getLanguage());
        assertThat(firstNewModel.getWord()).isSameAs(firstRecord.getWord());
        assertThat(firstNewModel.getTranslation()).isSameAs(firstRecord.getTranslation());

        assertThat(secondNewModel.getUser()).isSameAs(secondRecord.getUser());
        assertThat(secondNewModel.getLanguage()).isSameAs(secondRecord.getLanguage());
        assertThat(secondNewModel.getWord()).isSameAs(secondRecord.getWord());
        assertThat(secondNewModel.getTranslation()).isSameAs(secondRecord.getTranslation());
    }

    @Test
    public void shouldUpdateRecord(){
        //given
        firstWord = "Dog";
        firstTranslation = "Pies";
        secondTranslation = "Policjant";
        user = "Mateusz_Tchorek";
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        doNothing().when(recordRepository).updateRecord(firstWord, secondTranslation, user);

        //when
        recordService.updateRecord(firstWord, secondTranslation, user);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).updateRecord(firstWord, secondTranslation, user);
    }

    @Test
    public void shouldDeleteRecord(){
        //given
        firstWord = "Dog";
        firstTranslation = "Pies";
        secondTranslation = "Policjant";
        user = "Mateusz_Tchorek";
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        doNothing().when(recordRepository).deleteRecord(firstWord, secondTranslation, user);

        //when
        recordService.deleteRecord(firstWord, secondTranslation, user);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).deleteRecord(firstWord, secondTranslation, user);
    }
}

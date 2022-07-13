package com.tchorek.dictionary.record;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {

    private RecordRepository recordRepository;
    private RecordService recordService;
    private RecordEntity recordEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveRecord(){
        //given
        recordRepository = mock(RecordRepository.class);
        recordService = new RecordService(recordRepository);
        recordEntity = new RecordEntity(0,null,null,null,"test");
        when(recordRepository.save(recordEntity))
                .thenReturn(recordEntity);
        //when
        RecordEntity newRecord = recordService.saveRecord(recordEntity);
        //then
        Mockito.verify(recordRepository, Mockito.times(1)).save(recordEntity);
        assertThat(newRecord.getUser()).isSameAs(recordEntity.getUser());
    }


}

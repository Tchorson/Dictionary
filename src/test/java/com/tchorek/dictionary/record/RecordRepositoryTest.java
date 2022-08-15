package com.tchorek.dictionary.record;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(recordRepository).isNotNull();
    }

//    @Test
//    void injectedComponentsAreNotNull(){
//        assertThat(recordRepository).isNotNull();
//    }
//
//    @Test
//    void injectedComponentsAreNotNull(){
//        assertThat(recordRepository).isNotNull();
//    }
//
//    @Test
//    void injectedComponentsAreNotNull(){
//        assertThat(recordRepository).isNotNull();
//    }

}
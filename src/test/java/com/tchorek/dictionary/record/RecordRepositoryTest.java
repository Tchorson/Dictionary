package com.tchorek.dictionary.record;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
@Sql(scripts = "/repository/create-record.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/repository/cleanup-record.sql", executionPhase = AFTER_TEST_METHOD)
class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void injectedComponentIsNotNull(){
        assertThat(recordRepository).isNotNull();
    }

    @Test
    void testLoadDataForTestClass(){
        Iterable<RecordEntity> testList = recordRepository.findAll();
        assertEquals(1, IterableUtil.sizeOf(testList));
    }
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
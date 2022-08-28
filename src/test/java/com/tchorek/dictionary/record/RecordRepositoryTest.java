package com.tchorek.dictionary.record;

import com.tchorek.dictionary.language.Language;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Sql(scripts = "/repository/create-record.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "/repository/cleanup-record.sql", executionPhase = AFTER_TEST_METHOD)
class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    private final String firstWord = "dog";
    private final String firstWordTranslation = "pies";
    private final String firstWordLang = Language.ENGLISH.getLanguage();
    private final String user = "Mr_Test";
    private final String secondWord = "Die Katze";
    private final String secondWordTranslation = "Kot";
    private final String newTranslation = "new translation";
    private final int firstId = 1;
    private final int secondId = 2;
    private final int amount = 1;

    @Test
    void shouldInjectComponent() {
        assertThat(recordRepository).isNotNull();
    }

    @Test
    void shouldLoadData() {
        Iterable<RecordEntity> testList = recordRepository.findAll();
        RecordEntity first = testList.iterator().next();

        assertEquals(amount, IterableUtil.sizeOf(testList));
        assertEquals(first.getWord(), firstWord);
        assertEquals(first.getLanguage().getLanguage(), firstWordLang);
        assertEquals(first.getUser(), user);
        assertEquals(first.getTranslation(), firstWordTranslation);
        assertEquals(first.getId(), firstId);
    }

    @Test
    void shouldSaveData() {
        recordRepository.save(new RecordEntity(secondId, secondWord, secondWordTranslation, Language.GERMAN, user));
        Iterable<RecordEntity> testList = recordRepository.findAll();

        Iterator<RecordEntity> iterator = testList.iterator();
        RecordEntity first = iterator.next();
        RecordEntity second = iterator.next();

        assertEquals(first.getWord(), firstWord);
        assertEquals(first.getLanguage().getLanguage(), firstWordLang);
        assertEquals(first.getUser(), user);
        assertEquals(first.getTranslation(), firstWordTranslation);
        assertEquals(first.getId(), firstId);

        assertEquals(second.getWord(), secondWord);
        assertEquals(second.getLanguage().getLanguage(), Language.GERMAN.getLanguage());
        assertEquals(first.getUser(), user);
        assertEquals(second.getTranslation(), secondWordTranslation);
        assertEquals(second.getId(), secondId);
        assertEquals(2, IterableUtil.sizeOf(testList));
    }

    @Test
    void shouldUpdateData() {
        recordRepository.updateWordTranslation(firstWord, newTranslation, user);

        List<RecordEntity> words = recordRepository.findWordByUser(firstWord, user);
        RecordEntity word = words.get(0);

        assertEquals(amount, words.size());

        assertEquals(word.getWord(), firstWord);
        assertEquals(word.getLanguage().getLanguage(), firstWordLang);
        assertEquals(word.getUser(), user);
        assertEquals(word.getTranslation(), newTranslation);
        assertEquals(word.getId(), firstId);
    }

    @Test
    void shouldDeleteData() {
        recordRepository.deleteWord(firstWord, firstWordTranslation, user);

        List<RecordEntity> words = recordRepository.findWordByUser(firstWord, user);

        assertEquals(0, words.size());
    }

    @Test
    void shouldFindWordByUser() {
        List<RecordEntity> words = recordRepository.findWordByUser(firstWord, user);
        RecordEntity word = words.get(0);

        assertEquals(amount, words.size());

        assertEquals(word.getWord(), firstWord);
        assertEquals(word.getLanguage().getLanguage(), firstWordLang);
        assertEquals(word.getUser(), user);
        assertEquals(word.getTranslation(), firstWordTranslation);
        assertEquals(word.getId(), firstId);
    }

    @Test
    void shouldFindWordsByLanguage() {
        List<RecordEntity> words = recordRepository.findUserWordsByLanguage(user, Language.ENGLISH.getLanguage());
        RecordEntity word = words.get(0);

        assertEquals(amount, words.size());

        assertEquals(word.getWord(), firstWord);
        assertEquals(word.getLanguage().getLanguage(), firstWordLang);
        assertEquals(word.getUser(), user);
        assertEquals(word.getTranslation(), firstWordTranslation);
        assertEquals(word.getId(), firstId);
    }
}
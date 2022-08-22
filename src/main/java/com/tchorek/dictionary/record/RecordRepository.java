package com.tchorek.dictionary.record;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<RecordEntity, Integer> {

    @Modifying
    @Query(value = "DELETE FROM Vocabulary WHERE word = :word AND translation = :translation AND user = :user", nativeQuery = true)
    void deleteRecord(@Param("word") String word, @Param("translation") String translation, @Param("user") String user);

    @Modifying
    @Query(value = "UPDATE Vocabulary SET translation = :translation WHERE word = :word AND user = :user", nativeQuery = true)
    void updateRecord(@Param("word") String word, @Param("translation") String translation, @Param("user") String user);

    @Query(value = "SELECT * FROM Vocabulary WHERE word = :word AND user = :user", nativeQuery = true)
    List<RecordEntity> findWordByUser(@Param("word")String word, @Param("user") String user);

    @Query(value = "SELECT * FROM Vocabulary WHERE user = :user AND language = :language", nativeQuery = true)
    List<RecordEntity> getUserRecordsByLanguage(@Param("user") String user, @Param("language")String language);
}

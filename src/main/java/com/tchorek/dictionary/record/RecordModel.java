package com.tchorek.dictionary.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tchorek.dictionary.language.Language;
import lombok.Getter;

@Getter
public class RecordModel {
    @JsonInclude()
    private final String word;
    @JsonInclude()
    private final String translation;
    @JsonInclude()
    private final Language language;
    @JsonInclude()
    private final String user;

    @JsonCreator
    public RecordModel(@JsonProperty("word")String word,@JsonProperty("translation") String translation,@JsonProperty("language") Language language,@JsonProperty("user") String user) {
        this.word = word;
        this.translation = translation;
        this.language = language;
        this.user = user;
    }
}

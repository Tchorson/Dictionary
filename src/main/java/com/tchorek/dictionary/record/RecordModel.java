/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tchorek.dictionary.language.Language;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RecordModel {
    @JsonInclude()
    private String word;
    @JsonInclude()
    private String translation;
    @JsonInclude()
    private Language language;
    @JsonInclude()
    private String user;

    @JsonCreator
    public RecordModel(@JsonProperty("word")String word,@JsonProperty("translation") String translation,@JsonProperty("language") Language language,@JsonProperty("user") String user) {
        this.word = word;
        this.translation = translation;
        this.language = language;
        this.user = user;
    }
}

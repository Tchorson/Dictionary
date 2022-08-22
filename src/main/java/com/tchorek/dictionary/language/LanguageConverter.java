package com.tchorek.dictionary.language;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LanguageConverter implements AttributeConverter<Language, String> {

    @Override
    public String convertToDatabaseColumn(Language language) {
        return language.getLanguage();
    }

    @Override
    public Language convertToEntityAttribute(String s) {
        return Language.of(s);
    }
}

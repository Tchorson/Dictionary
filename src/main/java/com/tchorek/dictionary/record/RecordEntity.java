package com.tchorek.dictionary.record;

import com.tchorek.dictionary.language.Language;
import com.tchorek.dictionary.language.LanguageConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Vocabulary")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "Word")
    private String word;
    @Column(name = "Translation")
    private String translation;
    @Column(name = "Language")
    @Convert(converter = LanguageConverter.class)
    private Language language;
    @Column(name = "User")
    private String user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RecordEntity that = (RecordEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

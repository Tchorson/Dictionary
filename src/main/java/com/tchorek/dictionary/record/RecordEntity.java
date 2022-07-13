package com.tchorek.dictionary.record;

import com.tchorek.dictionary.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "records")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "word")
    private String word;
    @Column(name = "translation")
    private String translation;
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;
    @Column(name = "user")
    private String user;

    public RecordEntity(String word, String translation, Language language, String user){
        this.word = word.toLowerCase();
        this.translation = translation.toLowerCase();
        this.language = language;
        this.user = user;
    }

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

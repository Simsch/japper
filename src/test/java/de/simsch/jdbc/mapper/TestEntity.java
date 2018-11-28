package de.simsch.jdbc.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.sql.Array;

@Getter
@Setter
@NoArgsConstructor
public class TestEntity {

    private String stringField;
    private Integer integerField;
    private Array arrayField;
    @Convert(converter = IntToStringConverter.class)
    private String intToStringField;
    @Column(name = "special_column")
    private String annotatedStringField;
    @Column
    private String annotatedStringFieldFallback;
    private String nullField;
}

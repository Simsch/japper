package de.simsch.jdbc.mapper;

import javax.persistence.AttributeConverter;

public class IntToStringConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        return Integer.valueOf(attribute);
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return String.valueOf(dbData);
    }
}

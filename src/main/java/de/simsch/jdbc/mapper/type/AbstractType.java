package de.simsch.jdbc.mapper.type;

import de.simsch.jdbc.mapper.internal.CaseConverter;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractType {

    private final Field field;
    private ResultSet resultSet;

    AbstractType(Field field) {
        this.field = field;
    }

    Field getField() {
        return field;
    }

    String determineFieldName() {
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            return column.name().isEmpty() ? CaseConverter.toLowerUnderscore(field.getName()) : column.name();
        }
        return CaseConverter.toLowerUnderscore(field.getName());
    }

    ResultSet getResultSet() {
        return resultSet;
    }

    public AbstractType with(ResultSet resultSet) {
        this.resultSet = resultSet;
        return this;
    }

    public abstract void apply(Object entity) throws SQLException, IllegalAccessException;
}

package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class LongType extends AbstractType {

    LongType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Long value = super.getResultSet().getLong(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;

final class TimestampType extends AbstractType {

    TimestampType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Timestamp value = super.getResultSet().getTimestamp(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

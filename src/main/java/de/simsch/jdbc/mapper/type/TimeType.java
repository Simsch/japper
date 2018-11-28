package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Time;

final class TimeType extends AbstractType {

    TimeType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Time value = super.getResultSet().getTime(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

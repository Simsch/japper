package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;

final class DateType extends AbstractType {

    DateType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Date value = super.getResultSet().getDate(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

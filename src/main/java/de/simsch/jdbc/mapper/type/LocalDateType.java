package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;

final class LocalDateType extends AbstractType {

    LocalDateType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Date value = super.getResultSet().getDate(super.determineFieldName());
        super.getField().set(entity, value.toLocalDate());
    }
}

package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class StringType extends AbstractType {

    StringType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        String value = super.getResultSet().getString(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

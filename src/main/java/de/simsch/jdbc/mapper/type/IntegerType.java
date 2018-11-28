package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class IntegerType extends AbstractType {

    IntegerType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Integer value = super.getResultSet().getInt(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

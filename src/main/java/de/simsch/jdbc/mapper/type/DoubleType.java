package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class DoubleType extends AbstractType {

    DoubleType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Double value = super.getResultSet().getDouble(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

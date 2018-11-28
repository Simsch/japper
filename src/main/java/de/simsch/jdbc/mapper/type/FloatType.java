package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class FloatType extends AbstractType {

    FloatType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Float value = super.getResultSet().getFloat(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

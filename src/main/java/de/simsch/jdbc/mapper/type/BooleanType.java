package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class BooleanType extends AbstractType {

    BooleanType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Boolean value = super.getResultSet().getBoolean(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

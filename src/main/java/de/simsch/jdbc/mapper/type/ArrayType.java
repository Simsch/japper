package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.SQLException;

final class ArrayType extends AbstractType {

    ArrayType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Array value = super.getResultSet().getArray(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

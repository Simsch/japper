package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class BytesType extends AbstractType {

    BytesType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        byte[] value = super.getResultSet().getBytes(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

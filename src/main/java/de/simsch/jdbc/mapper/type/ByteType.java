package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class ByteType extends AbstractType {

    ByteType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Byte value = super.getResultSet().getByte(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

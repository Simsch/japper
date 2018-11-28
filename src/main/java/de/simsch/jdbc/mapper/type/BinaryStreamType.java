package de.simsch.jdbc.mapper.type;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;

final class BinaryStreamType extends AbstractType {

    BinaryStreamType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        InputStream value = super.getResultSet().getBinaryStream(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

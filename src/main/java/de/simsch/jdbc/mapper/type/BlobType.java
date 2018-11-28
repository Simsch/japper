package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;

final class BlobType extends AbstractType {

    BlobType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Blob value = super.getResultSet().getBlob(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

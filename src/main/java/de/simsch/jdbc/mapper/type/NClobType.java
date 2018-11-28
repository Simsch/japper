package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.NClob;
import java.sql.SQLException;

final class NClobType extends AbstractType {

    NClobType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        NClob value = super.getResultSet().getNClob(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.Clob;
import java.sql.SQLException;

final class ClobType extends AbstractType {

    ClobType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Clob value = super.getResultSet().getClob(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

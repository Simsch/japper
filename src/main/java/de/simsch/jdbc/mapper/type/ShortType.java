package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class ShortType extends AbstractType {

    ShortType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Short value = super.getResultSet().getShort(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

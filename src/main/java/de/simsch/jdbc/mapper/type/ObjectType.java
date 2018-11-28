package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.sql.SQLException;

final class ObjectType extends AbstractType {

    ObjectType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Object value = super.getResultSet().getObject(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

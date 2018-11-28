package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;

final class URLType extends AbstractType {

    URLType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        URL value = super.getResultSet().getURL(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

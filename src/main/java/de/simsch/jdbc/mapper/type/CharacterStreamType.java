package de.simsch.jdbc.mapper.type;

import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.SQLException;

final class CharacterStreamType extends AbstractType {

    CharacterStreamType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Reader value = super.getResultSet().getCharacterStream(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

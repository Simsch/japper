package de.simsch.jdbc.mapper.type;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;

final class BigDecimalType extends AbstractType {

    BigDecimalType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        BigDecimal value = super.getResultSet().getBigDecimal(super.determineFieldName());
        super.getField().set(entity, value);
    }
}

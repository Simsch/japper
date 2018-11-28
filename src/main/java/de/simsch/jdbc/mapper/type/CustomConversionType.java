package de.simsch.jdbc.mapper.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

final class CustomConversionType extends AbstractType {

    CustomConversionType(Field field) {
        super(field);
    }

    @Override
    public void apply(Object entity) throws SQLException, IllegalAccessException {
        Convert convert = super.getField().getAnnotation(Convert.class);
        AttributeConverter<?, Object> converter = createInstance(convert, entity);
        Object dbValue = super.getResultSet().getObject(super.determineFieldName());
        Object convertedValue = converter.convertToEntityAttribute(dbValue);
        super.getField().set(entity, convertedValue);
    }

    @SuppressWarnings("unchecked")
    private AttributeConverter<?, Object> createInstance(Convert convert, Object entity) {
        try {
            return (AttributeConverter<?, Object>) convert.converter().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalStateException("Can not create instance of custom convert for field " +
                                                    super.determineFieldName() + " in object " + entity.getClass() + "!");
        }
    }
}

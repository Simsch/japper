package de.simsch.jdbc.mapper.type;

import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Transient;

public final class TypeBuilder {

    private static final Map<Class<?>, Class<? extends AbstractType>> TYPES = new HashMap<Class<?>, Class<? extends AbstractType>>() {{
        put(Array.class, ArrayType.class);
        put(BigDecimal.class, BigDecimalType.class);
        put(Blob.class, BlobType.class);
        put(Boolean.class, BooleanType.class);
        put(byte[].class, BytesType.class);
        put(Byte.class, ByteType.class);
        put(Reader.class, CharacterStreamType.class);
        put(Clob.class, ClobType.class);
        put(Date.class, DateType.class);
        put(Double.class, DoubleType.class);
        put(Float.class, FloatType.class);
        put(Integer.class, IntegerType.class);
        put(LocalDate.class, LocalDateType.class);
        put(LocalDateTimeType.class, LocalDateTimeType.class);
        put(Long.class, LongType.class);
        put(NClob.class, NClobType.class);
        put(Object.class, ObjectType.class);
        put(Short.class, ShortType.class);
        put(String.class, StringType.class);
        put(Timestamp.class, TimestampType.class);
        put(Time.class, TimeType.class);
        put(URL.class, URLType.class);
    }};

    private TypeBuilder() {}

    public static AbstractType from(Field field) {
        try {
            if (field.isAnnotationPresent(Transient.class)) {
                return new TransientType(field);
            }
            if (field.isAnnotationPresent(Convert.class) && !field.getAnnotation(Convert.class).disableConversion()) {
                return new CustomConversionType(field);
            }
            return TYPES.get(field.getType()).getDeclaredConstructor(Field.class).newInstance(field);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not create mapping type for " + field.getType() + "!", e);
        }
    }
}

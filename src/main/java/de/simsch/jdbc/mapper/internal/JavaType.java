package de.simsch.jdbc.mapper.internal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class JavaType<T> {

    private final Class<T> type;

    private JavaType(Class<T> type) {
        this.type = type;
    }

    public static <T> JavaType<T> of(Class<T> type) {
        if (type == null) {
            throw new IllegalStateException("JavaType of type 'null' impossible!");
        }
        return new JavaType<>(type);
    }

    public Class<T> getType() {
        return type;
    }

    public String getClassName() {
        return type.getName();
    }

    public Field[] getDeclaredFields() {
        return type.getDeclaredFields();
    }

    public T createInstance() {
        try {
            return type.getConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Mapper could not instantiate class " + type + "! No default constructor present?", e);
        }
    }
}

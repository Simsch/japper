package de.simsch.jdbc.mapper;

import de.simsch.jdbc.mapper.internal.JavaType;

public class Mapper {

    public static <Entity> ResultSetParser<Entity> into(Class<Entity> clazz) {
        if (clazz == null) {
            throw new NullPointerException("Given class to map into is 'null'!");
        }
        return new ResultSetParser<>(JavaType.of(clazz));
    }
}

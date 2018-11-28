package de.simsch.jdbc.mapper;

import de.simsch.jdbc.mapper.internal.JavaType;
import de.simsch.jdbc.mapper.type.TypeBuilder;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetParser<Entity> {

    private final JavaType<Entity> type;

    ResultSetParser(JavaType<Entity> type) {
        this.type = type;
    }

    public Entity map(ResultSet resultSet) {
        if (resultSet == null) {
            throw new NullPointerException("Can not map result set 'null' with class " + type.getClassName() + "!");
        }
        Entity object = type.createInstance();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                TypeBuilder.from(field).with(resultSet).apply(object);
            } catch (SQLException e) {
                throw new IllegalStateException("Can not extract field " + field.getName(), e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Can not set field " + field.getName() + " to " + object.getClass() + "!");
            }
        }
        return object;
    }
}

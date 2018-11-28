package de.simsch.jdbc.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapperTest {

    private static final String STRING_VALUE = "String value";
    private static final Integer INTEGER_VALUE = 42;
    private static final Object OBJECT_VALUE = 43;
    private static final Array ARRAY_VALUE = new MockArray(new String[]{"Hello", "there"});

    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        resultSet = mock(ResultSet.class);
        when(resultSet.getString("string_field")).thenReturn(STRING_VALUE);
        when(resultSet.getInt("integer_field")).thenReturn(INTEGER_VALUE);
        when(resultSet.getArray(anyString())).thenReturn(ARRAY_VALUE);
        when(resultSet.getObject(anyObject())).thenReturn(OBJECT_VALUE);
        when(resultSet.getString("special_column")).thenReturn(STRING_VALUE);
        when(resultSet.getString("annotated_string_field_fallback")).thenReturn(STRING_VALUE);
        when(resultSet.getString("null_field")).thenReturn(null);
    }

    @Test
    @DisplayName("Check all possible fields to be mapped into the given class")
    void testMapperFields() {
        TestEntity result = Mapper.into(TestEntity.class).map(resultSet);

        assertThat(result.getStringField()).isEqualTo(STRING_VALUE);
        assertThat(result.getIntegerField()).isEqualTo(INTEGER_VALUE);
        assertThat(result.getArrayField()).isEqualTo(ARRAY_VALUE);
        assertThat(result.getIntToStringField()).isEqualTo(String.valueOf(OBJECT_VALUE));
        assertThat(result.getAnnotatedStringField()).isEqualTo(STRING_VALUE);
        assertThat(result.getAnnotatedStringFieldFallback()).isEqualTo(STRING_VALUE);
        assertThat(result.getNullField()).isNull();
    }

    @Test
    @DisplayName("Check if the mapper work with null class")
    void testMapperWithNull() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> Mapper.into(null).map(resultSet));
        assertThat(ex.getMessage()).isNotBlank();
    }

    @Test
    @DisplayName("Check if the mapper work with null result set")
    void testMapperWithResultSetNull() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> Mapper.into(TestEntity.class).map(null));
        assertThat(ex.getMessage()).contains(TestEntity.class.getName());
    }
}

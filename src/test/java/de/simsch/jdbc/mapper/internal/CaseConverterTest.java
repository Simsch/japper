package de.simsch.jdbc.mapper.internal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CaseConverterTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("stringField", "string_field"),
                Arguments.of("longStringField", "long_string_field"),
                Arguments.of("StringField", "string_field"),
                Arguments.of("STRING_FIELD", "string_field"));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void testCamelCaseConversion(String input, String output) {
        assertThat(CaseConverter.toLowerUnderscore(input)).isEqualTo(output);
    }

    @Test
    @DisplayName("Check null case")
    void toLowerUnderscoreWithNull() {
        assertThrows(NullPointerException.class, () -> CaseConverter.toLowerUnderscore(null));
    }
}
package de.simsch.jdbc.mapper.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

class JavaTypeTest {

    private final JavaType<TestClass> underTest = JavaType.of(TestClass.class);

    @Test
    void createWithNullClass() {
        assertThrows(IllegalStateException.class, () -> JavaType.of(null));
    }

    @Test
    void getType() {
        assertThat(underTest.getType()).isEqualTo(TestClass.class);
    }

    @Test
    void getClassName() {
        assertThat(underTest.getClassName()).isEqualTo(TestClass.class.getName());
    }

    @Test
    void getDeclaredFields() {
        assertThat(underTest.getDeclaredFields()).isEqualTo(TestClass.class.getDeclaredFields());
    }

    @Test
    void createInstance() {
        assertThat(underTest.createInstance()).isEqualTo(new TestClass());
    }

    @Test
    void createInstanceNotPossible() {
        assertThrows(IllegalStateException.class, () -> JavaType.of(UnInstantiatableTestClass.class).createInstance());
    }

    @EqualsAndHashCode
    @NoArgsConstructor
    private static class TestClass {
        private String someField;
        private String otherField;
    }

    private static class UnInstantiatableTestClass {
        private String someField;
        private String otherField;
    }
}

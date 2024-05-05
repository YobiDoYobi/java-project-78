package app;

import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorNumberTest {
    private static NumberSchema schema;

    @BeforeEach
    public void beforeEach() {
        Validator v = new Validator();
        schema = v.number();
    }

    @Test
    void testIntegerRequired() {
        assertThat(schema.isValid(5)).isTrue();
        // Пока не вызван метод required(), null считается валидным
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }
    @Test
    void testIntegerPositive() {
        schema.positive();
        // Потому что ранее мы вызвали метод positive()
        assertThat(schema.isValid(-10)).isFalse();
        //  Ноль — не положительное число
        assertThat(schema.isValid(0)).isFalse();
    }
    @Test
    void testIntegerPositiveNull() {
        schema.positive();
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
    @Test
    void testIntegerRange() {
        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }
    @Test
    void testIntegerRangeNull() {
        schema.range(5, 10);
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
}

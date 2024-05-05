package app;

import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorStringTest {
    private static Validator v;
    private static StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    void testStringRequiredNull() {
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    void testStringRequiredEmpty() {
        assertThat(schema.isValid("")).isTrue();
        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    void testStringContains() {
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
    }

    @Test
    void testStringMinLength() {
        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        assertThat(schema.minLength(10).minLength(4).isValid("Hexlet")).isTrue(); // true
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
}

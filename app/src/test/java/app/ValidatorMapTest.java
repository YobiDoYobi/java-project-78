package app;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorMapTest {
    private static Validator v;
    private static MapSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void testSimpleMapRequired() {
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testSimpleMapSizeof() {
        schema.sizeof(2);
        Map<String, String> data = new HashMap<>();
        assertThat(schema.isValid(data)).isFalse();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testShapeString() {
        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", v.string().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", v.string().required().minLength(2));
        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schema.shape(schemas);
        // Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertThat(schema.isValid(human2)).isFalse();

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertThat(schema.isValid(human3)).isFalse();
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testShapeNumber() {
        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemas.put("1", v.number().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("2", v.number().required().positive());
        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schema.shape(schemas);
        // Проверяем объекты
        Map<String, Integer> data1 = new HashMap<>();
        data1.put("1", 23);
        data1.put("2", -3);
        assertThat(schema.isValid(data1)).isFalse();

        Map<String, Integer> data2 = new HashMap<>();
        data2.put("1", 23);
        data2.put("2", 1);
        assertThat(schema.isValid(data2)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
}

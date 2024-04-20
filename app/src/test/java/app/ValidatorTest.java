package app;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    void testString() {
        var v = new Validator();
        var schema = v.string();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
    }

    @Test
    void testInteger() {
        var v = new Validator();
        var schema = v.number();
        assertThat(schema.isValid(5)).isTrue();
        // Пока не вызван метод required(), null считается валидным
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        // Потому что ранее мы вызвали метод positive()
        assertThat(schema.isValid(-10)).isFalse();
        //  Ноль — не положительное число
        assertThat(schema.isValid(0)).isFalse();
        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testMap() {
        var v = new Validator();
        MapSchema<String> schema = v.map();
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();
        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testShape() {
        var v = new Validator();
        //var schema = v.map();
        MapSchema<String> schema = v.map();
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
    }
}

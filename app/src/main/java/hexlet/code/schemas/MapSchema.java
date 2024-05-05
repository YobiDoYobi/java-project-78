package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", m -> m.size() >= size);
        return this;
    }

    /*public void shape(Map<String, BaseSchema<T>> schemas) {
        schemas.forEach((s, tBaseSchema) -> addCheck(s, m -> {
            return schemas.entrySet().stream().allMatch(e -> {
                T obj = m.get(e.getKey());
                return e.getValue().isValid(obj);
            });
        }));
    }*/
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(
                "shape",
                value -> schemas.entrySet().stream().allMatch(
                        schema -> {
                            T o = (T) value.get(schema.getKey());
                            return schema.getValue().isValid(o);
                        }
                )
        );
        return this;
    }

    public MapSchema() {
        addCheck("requiredMap", Objects::nonNull);
    }
}

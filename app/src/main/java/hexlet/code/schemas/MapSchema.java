package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema<T> extends BaseSchema<Map<T, T>> {
    public MapSchema<T> required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        addCheck("sizeof", m -> m.size() >= size);
        return this;
    }

    public void shape(Map<T, BaseSchema<T>> schemas) {
        schemas.forEach((s, tBaseSchema) -> addCheck((String) s, m -> {
            return schemas.entrySet().stream().allMatch(e -> {
                BaseSchema<T> schema = e.getValue();
                T obj = m.get(e.getKey());
                //T obj = e.getKey();
                return schema.isValid(obj);
            });
        }));
        /*addCheck(
                "shape",
                map -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        BaseSchema<T> schema = e.getValue();
                        T obj = map.get(e.getKey());
                        return schema == null || schema.isValid(obj);
                    });
                }
        );*/
    }
}

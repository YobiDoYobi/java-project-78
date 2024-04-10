package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addCheck("required", m -> m instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", m -> ((Map<?, ?>) m).size() >= size);
        return this;
    }
}

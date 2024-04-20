package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public <T> MapSchema map() {
        return new MapSchema<T>();
    }
}

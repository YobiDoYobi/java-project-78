package hexlet.code.schemas;

import java.util.LinkedHashMap;

public class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int minLengthIn) {
        addCheck("minLength", s -> s.length() > minLengthIn);
        return this;
    }

    public StringSchema contains(String subStrIn) {
        addCheck("contains", s -> s.startsWith(subStrIn));
        return this;
    }

    public StringSchema required() {
        super.required = true;
        return this;
    }

    public StringSchema() {
        super.checks = new LinkedHashMap<>();
        addCheck("requiredString", s -> s != null && !s.isEmpty());
    }
}

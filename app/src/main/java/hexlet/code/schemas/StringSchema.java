package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int minLengthIn) {
        addCheck("minLength", s -> s != null && s.length() > minLengthIn);
        return this;
    }

    public StringSchema contains(String subStrIn) {
        addCheck("contains", s -> s.startsWith(subStrIn));
        return this;
    }

    public StringSchema required() {
        addCheck("required", s -> s != null && !s.isEmpty());
        return this;
    }
}

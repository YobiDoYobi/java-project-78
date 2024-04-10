package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public StringSchema minLength(int minLengthIn) {
        addCheck("minLength", s -> ((String) s).length() > minLengthIn);
        return this;
    }

    public StringSchema contains(String subStrIn) {
        addCheck("contains", s -> ((String) s).startsWith(subStrIn));
        return this;
    }

    public StringSchema required() {
        addCheck("required", s -> s instanceof String && !((String) s).isEmpty());
        return this;
    }
}

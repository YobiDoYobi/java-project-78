package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int minLengthIn) {
        addCheck("minLength", s -> s.length() > minLengthIn);
        return this;
    }

    public StringSchema contains(String subStrIn) {
        addCheck("contains", s -> s.contains(subStrIn));
        return this;
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public StringSchema() {
        addCheck("requiredString", s -> s != null && !s.isEmpty());
    }
}

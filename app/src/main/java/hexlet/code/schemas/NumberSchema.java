package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        super.required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", i -> i > 0);
        return this;
    }

    public NumberSchema range(int minRangeIn, int maxRangeIn) {
        addCheck("range", i -> i >= minRangeIn && i <= maxRangeIn);
        return this;
    }

    public NumberSchema() {
        addCheck("requiredNumber", Objects::nonNull);
    }
}

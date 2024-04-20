package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", i -> i == null || i > 0);
        return this;
    }

    public NumberSchema range(int minRangeIn, int maxRangeIn) {
        addCheck("range", i -> i >= minRangeIn && i <= maxRangeIn);
        return this;
    }
}

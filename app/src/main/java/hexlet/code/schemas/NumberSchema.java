package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        //addCheck("required", integer -> ((Integer) integer) != null);
        addCheck("required", i -> i instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", i -> i == null || ((int) i) > 0);
        return this;
    }

    public NumberSchema range(int minRangeIn, int maxRangeIn) {
        addCheck("range", i -> ((Integer) i) >= minRangeIn && ((Integer) i) <= maxRangeIn);
        return this;
    }
}

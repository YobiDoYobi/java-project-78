package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private LinkedHashMap<String, Predicate<T>> checks = new LinkedHashMap<>();;
    private boolean required;

    protected final void setRequired(boolean requiredIn) {
        this.required = requiredIn;
    }

    final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public final boolean isValid(T object) {
        if (required || checks.firstEntry().getValue().test(object)) {
            return checks.values().stream().allMatch(check -> check.test(object));
        }
        return true;
    }
}

package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public final boolean isValid(T object) {
        return checks.values().stream().allMatch(check -> check.test(object));
    }
}

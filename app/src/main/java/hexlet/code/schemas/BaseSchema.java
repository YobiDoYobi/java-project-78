package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private LinkedHashMap<String, Predicate<T>> checks = new LinkedHashMap<>();;
    boolean required;

    /*protected BaseSchema() {
        this.checks = new LinkedHashMap<>();
    }*/

    void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public boolean isValid(T object) {
        if (required || checks.firstEntry().getValue().test(object)) {
            return checks.values().stream().allMatch(check -> check.test(object));
        }
        return true;
    }
}

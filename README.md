### Hexlet tests and linter status:

[![Actions Status](https://github.com/YobiDoYobi/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/YobiDoYobi/java-project-78/actions)
[![gia-check](https://github.com/YobiDoYobi/java-project-78/actions/workflows/gia-check.yml/badge.svg)](https://github.com/YobiDoYobi/java-project-78/actions/workflows/gia-check.yml)
<a href="https://codeclimate.com/github/YobiDoYobi/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/af9893510c73f29ce0fc/maintainability" /></a>
<a href="https://codeclimate.com/github/YobiDoYobi/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/af9893510c73f29ce0fc/test_coverage" /></a>

# Description

Validator of data

1. Create Validator

```
Validator v = new Validator();
```

2. Create one of 3 schemas

```
MapSchema schema = v.map();
NumberSchema schema = v.number();
StringSchema schema = v.string();
```
3. Configure schema:
```
schema.required(); // check for not null object
// for StringSchema
schema.contains("text for check"); // check for String contain "text for check"
schema.minLength(10); // check for min length of String
// for NumberSchema
schema.positive(); // check for Number > 0
schema.range(5, 10); // check for 5 <= Number <= 10
// for MapSchema
schema.sizeof(2); // check for size of Map > 2
// nested objects in Map
schemas.put("firstName", v.string().required()); // configure StringSchema for firstName
schemas.put("lastName", v.string().required().minLength(2)); // configure StringSchema for lastName
schema.shape(schemas); // add all schemas to validate
```
4. Run check
```
schema.isValid(object); // run check for object
// for StringSchema
schema.isValid("hexlet"); // return true/false for String "hexlet"
// for NumberSchema
schema.isValid(5); // return true/false for Number 5
// for MapSchema
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(5); // return true/false for Map data
// nested objects in Map
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(human1);
```

### Test, checkstyle and build

```bash
make build
```

### Run tests

```bash
make test
```

### Run checkstyle

```bash
make lint
```
## Json Utils ##

A collection of useful JSON utilities.

Object to JSON

```java
String json = JsonUtils.objectToJson(myObject);
```

JSON to Object

```java
MyClass myObject = JsonUtils.jsonToObject(json, MyClass.class);
```

JSON to List

```java
List<MyClass> myObject = JsonUtils.jsonToList(json, MyClass.class);
```

JSON to Array

```java
MyClass[] myArray = JsonUtils.jsonToArray(json, MyClass.class);
```

Validate String is a valid JSON

```java
JsonUtils.isValid(json);
```

Determine if two JSON Strings are equal

```java
JsonUtils.areEqual(json1, json2);
```
**Note:** Ignores carriage returns, line feeds, tabs, etc.


## JSON Utils ##

A collection of useful JSON utilities.

Object to JSON

```java
String json = JsonUtils.objectToJson(myObject);
```

Object to pretty printed JSON

```java
String prettyPrinted = JsonUtils.prettyPrint(myObject);
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

JSON to HashMap

```java
Map<String, MyClass> map = JsonUtils.jsonToHashMap(json, String.class, MyClass.class);
```

Check if String is a valid JSON

```java
JsonUtils.isValid(json);
```

Determine if two JSON Strings are equal

```java
JsonUtils.areEqual(json1, json2);
```
**Note:** Ignores carriage returns, line feeds, tabs, etc.

Convert to CSV

```java
String csv = JsonUtils.toCSV(json); 
```

**Note:** JSON must be an array of objects.

Convert to CSV with defined delimiter

```java
String csv = JsonUtils.toCSV(json, '|'); 
```

**Note:** JSON must be an array of objects and delimiter must be a character.

JSON to YAML

```java
String yaml = JsonUtils.toYAML(json);
```

package io.ljunggren.json.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class JsonUtils {

    public static String objectToJson(Object object) throws JsonProcessingException {
        return objectToJson(object, new ObjectMapper());
    }
    
    public static String prettyPrint(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("    ", DefaultIndenter.SYS_LF));
        if (object instanceof String) {
            return mapper.writer(printer).writeValueAsString(JsonUtils.jsonToObject((String) object, Object.class));
        }
        return mapper.writer(printer).writeValueAsString(object);
    }
    
    private static String objectToJson(Object object, ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
    
    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, clazz);
    }
    
    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(json, type);
    }
    
    public static <T> T[] jsonToArray(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructArrayType(clazz);
        return mapper.readValue(json, type);
    }
    
    public static <K, V> Map<K, V> jsonToHashMap(String json, Class<K> keyClass, Class<V> valueClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
        return mapper.readValue(json, type);
    }
    
    public static boolean isValid(String json) {
        try {
            new ObjectMapper().readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean areEqual(String json1, String json2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json1).equals(mapper.readTree(json2));
    }
    
    public static String toCSV(String input) throws IOException {
        return toCSV(input, ',');
    }
    
    public static String toCSV(String input, char delimiter) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(input);
        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> csvSchemaBuilder.addColumn(fieldName));
        CsvSchema csvSchema = csvSchemaBuilder.build()
                .withHeader()
                .withColumnSeparator(delimiter)
                .withoutQuoteChar();
        CsvMapper csvMapper = new CsvMapper();
        return csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValueAsString(jsonTree);
    }
    
    public static String toYAML(String json) throws JsonProcessingException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(json);
        return new YAMLMapper().writeValueAsString(jsonNodeTree);
    }
    
    public static String toTOML(String json) throws JsonProcessingException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(json);
        return new TomlMapper().writeValueAsString(jsonNodeTree).replaceAll("'", "\"");
    }
    
    public static String toXML(String json) throws JsonProcessingException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(json);
        return new XmlMapper().writeValueAsString(jsonNodeTree);
    }
    
}

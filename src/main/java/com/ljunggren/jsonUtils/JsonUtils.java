package com.ljunggren.jsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    public static String objectToJson(Object object) throws JsonProcessingException {
        return objectToJson(object, new ObjectMapper());
    }
    
    public static String prettyPrint(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectToJson(object, mapper);
    }
    
    private static String objectToJson(Object object, ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
    
    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, clazz);
    }
    
    public static <T> List<T> jsonToList(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(json, type);
    }
    
    public static <T> T[] jsonToArray(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructArrayType(clazz);
        return mapper.readValue(json, type);
    }
    
    public static <K, V> Map<K, V> jsonToHashMap(String json, Class<K> keyClass, Class<V> valueClass) throws JsonMappingException, JsonProcessingException {
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
    
    public static boolean areEqual(String json1, String json2) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json1).equals(mapper.readTree(json2));
    }
    
}

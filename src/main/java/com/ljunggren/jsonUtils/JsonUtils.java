package com.ljunggren.jsonUtils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
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
    
    public static boolean isValid(String json) {
        try {
            new ObjectMapper().readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}

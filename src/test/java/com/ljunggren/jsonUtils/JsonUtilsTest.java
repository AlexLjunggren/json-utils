package com.ljunggren.jsonUtils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JsonUtilsTest {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String name;
        private int age;
        private boolean active;
        
    }

    @Test
    public void objectToJsonTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "{\"name\":\"Alex\",\"age\":40,\"active\":true}";
        String actual = JsonUtils.objectToJson(user);
        assertEquals(expected, actual);
    }
    
    @Test
    public void prettyPrintTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "{\r\n"
                + "  \"name\" : \"Alex\",\r\n"
                + "  \"age\" : 40,\r\n"
                + "  \"active\" : true\r\n"
                + "}"
                + "";
        String actual = JsonUtils.prettyPrint(user);
        assertEquals(expected, actual);
    }
    
    @Test
    public void jsonToObjectTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String json = JsonUtils.objectToJson(user);
        User generatedUser = JsonUtils.jsonToObject(json, User.class);
        assertEquals(user, generatedUser);
    }
    
    @Test
    public void jsonToListTest() throws JsonMappingException, JsonProcessingException {
        List<User> users = Arrays.asList(new User[] {
                new User("Alex", 40, true),
                new User("James", 10, false)
        });
        String json = JsonUtils.objectToJson(users);
        List<User> generatedUsers = JsonUtils.jsonToList(json, User.class);
        assertEquals(users, generatedUsers);
    }
    
    @Test
    public void jsonToArrayTest() throws JsonProcessingException {
        User[] users = new User[] {
                new User("Alex", 40, true),
                new User("James", 10, false)
        };
        String json = JsonUtils.objectToJson(users);
        User[] generatedUsers = JsonUtils.jsonToArray(json, User.class);
        assertArrayEquals(users, generatedUsers);
    }
    
    @Test
    public void jsonToHashMapTest() throws JsonProcessingException {
        Map<String, User> map = new HashMap<>();
        map.put("Employee", new User("Alex", 40, true));
        map.put("Boss",new User("James", 10, false));
        String json = JsonUtils.objectToJson(map);
        Map<String, User> generatedMap = JsonUtils.jsonToHashMap(json, String.class, User.class);
        assertEquals(map, generatedMap);
    }
    
    @Test
    public void isValidTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String json = JsonUtils.objectToJson(user);
        assertTrue(JsonUtils.isValid(json));
    }
    
    @Test
    public void isValidNullTest() {
        assertFalse(JsonUtils.isValid(null));
    }
    
    @Test
    public void isValidFalseTest() {
        assertFalse(JsonUtils.isValid("{\"name\":\""));
    }
    
    @Test
    public void areEqualTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "{\"name\":\"Alex\",\"age\":40,\"active\":true}";
        String actual = JsonUtils.objectToJson(user);
        assertTrue(JsonUtils.areEqual(expected, actual));
    }

    @Test
    public void areEqualFalseTest() throws JsonProcessingException {
        User user = new User("Alex", 40, true);
        String expected = "{\"name\":\"Alexander\",\"age\":40,\"active\":true}";
        String actual = JsonUtils.objectToJson(user);
        assertFalse(JsonUtils.areEqual(expected, actual));
    }

}

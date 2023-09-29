package com.example.springbootfullproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}

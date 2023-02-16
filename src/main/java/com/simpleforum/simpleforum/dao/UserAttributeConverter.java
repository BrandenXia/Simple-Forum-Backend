package com.simpleforum.simpleforum.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UserAttributeConverter implements AttributeConverter<Map<String, Object>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    final Logger logger = LoggerFactory.getLogger(getClass());

    // Convert Map<String, Object> to String
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String result = null;

        try {
            result = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error: {}", e.getMessage());
        }

        return result;
    }

    // Convert String to Map<String, Object>
    @Override
    public Map<String, Object> convertToEntityAttribute(String userAttributeJSON) {
        Map<String, Object> result = null;

        try {
            result = objectMapper.readValue(userAttributeJSON, new TypeReference<HashMap<String, Object>>() {});
        } catch (final JsonProcessingException e) {
            logger.error("JSON reading error: {}", e.getMessage());
        }
        return result;
    }
}
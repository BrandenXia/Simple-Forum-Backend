package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.repository.UserAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.IOException;
import java.util.Map;

@Entity
@Table(name = "user_attributes")
@Data
public class UserAttribute {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Id
    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    @Transient
    private String UserAttributeJSON;
    @Convert(converter = UserAttributeConverter.class)
    private Map<String, Object> attributes;

    public UserAttribute() {
    }

    public void serializeAttributes() throws JsonProcessingException {
        this.UserAttributeJSON = objectMapper.writeValueAsString(this.attributes);
    }

    public void deserializeAttributes() throws IOException {
        this.attributes = objectMapper.readValue(this.UserAttributeJSON, new TypeReference<>() {
        });
    }
}

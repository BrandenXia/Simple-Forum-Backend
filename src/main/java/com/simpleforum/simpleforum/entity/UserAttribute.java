package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.repository.UserAttributeConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Map;

@Entity
@Table(name = "user_attributes")
@EqualsAndHashCode
public class UserAttribute {
    @Id
    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Getter
    @Setter
    private User user;

    @Transient
    @Getter
    @Setter
    private String UserAttributeJSON;

    @Convert(converter = UserAttributeConverter.class)
    @Getter
    @Setter
    private Map<String, Object> attributes;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void serializeAttributes() throws JsonProcessingException {
        this.UserAttributeJSON = objectMapper.writeValueAsString(this.attributes);
    }

    public void deserializeAttributes() throws IOException {
        this.attributes = objectMapper.readValue(this.UserAttributeJSON, new TypeReference<>() {
        });
    }

    public UserAttribute() {
    }
}

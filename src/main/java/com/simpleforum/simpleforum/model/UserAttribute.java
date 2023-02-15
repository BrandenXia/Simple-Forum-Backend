package com.simpleforum.simpleforum.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.converter.UserAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Map;

@Entity
@Table(name = "user_attributes")
public class UserAttribute {
    @Id
    @JoinColumn(name = "user_id")
    @OneToOne
    @Getter @Setter private User user;

    @Column
    @Getter @Setter private String customerAttributeJSON;

    @Convert(converter = UserAttributeConverter.class)
    @Getter @Setter private Map<String, String> attributes;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void serializeAttributes() throws JsonProcessingException {
        this.customerAttributeJSON = objectMapper.writeValueAsString(this.attributes);
    }

    public void deserializeAttributes() throws IOException {
        this.attributes = objectMapper.readValue(this.customerAttributeJSON, new TypeReference<Map<String, String>>() {});
    }

    public UserAttribute() {
    }
}

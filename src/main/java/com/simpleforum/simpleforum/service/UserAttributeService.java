package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.UserAttribute;

import java.util.Map;

public interface UserAttributeService {
    void setUserAttribute(String user_id, Map<String, Object> attributes);
    void updateUserAttribute(String user_id, Map<String, Object> attributes);
    void deleteUserAttribute(String user_id);
    UserAttribute getUserAttribute(String user_id);
}

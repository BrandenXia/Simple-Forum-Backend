package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Role;

import java.util.Set;

public interface RoleService {
    Role createRole(String name, Set<Permission> permissions);

    void deleteRole(String name);

    Set<Permission> getPermissions(String name);

    void addPermission(String name, Permission permission);

    void addPermissions(String name, Set<Permission> permissions);

    void removePermission(String name, Permission permission);

    void removePermissions(String name, Set<Permission> permissions);
}

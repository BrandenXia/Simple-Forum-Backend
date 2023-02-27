package com.simpleforum.simpleforum.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Role;
import com.simpleforum.simpleforum.repository.RoleRepository;
import com.simpleforum.simpleforum.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(String name, Set<Permission> permissions) {
        if (roleRepository.existsByName(name)) {
            throw new RuntimeException("Role already exists");
        }
        Role role = new Role();
        role.setId(NanoIdUtils.randomNanoId());
        role.setName(name);
        role.setPermissions(permissions);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(String name) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        roleRepository.delete(role);
    }

    @Override
    public Set<Permission> getPermissions(String name) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        return role.getPermissions();
    }

    @Override
    public void addPermission(String name, Permission permission) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        Set<Permission> permissions = role.getPermissions();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Override
    public void addPermissions(String name, Set<Permission> permissions) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        Set<Permission> rolePermissions = role.getPermissions();
        rolePermissions.addAll(permissions);
        role.setPermissions(rolePermissions);
        roleRepository.save(role);
    }

    @Override
    public void removePermission(String name, Permission permission) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        Set<Permission> permissions = role.getPermissions();
        permissions.remove(permission);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Override
    public void removePermissions(String name, Set<Permission> permissions) {
        if (!roleRepository.existsByName(name)) {
            throw new RuntimeException("Role does not exist");
        }
        Role role = roleRepository.findByName(name);
        Set<Permission> rolePermissions = role.getPermissions();
        rolePermissions.removeAll(permissions);
        role.setPermissions(rolePermissions);
        roleRepository.save(role);
    }
}

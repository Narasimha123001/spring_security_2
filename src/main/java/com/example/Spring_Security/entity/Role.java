package com.example.Spring_Security.entity;

import java.security.Permission;
import java.util.Collections;
import java.util.Set;

public enum Role {

    ADMIN(Set.of(Permissions.READ, Permissions.WRITE, Permissions.DELETE)),
    USER(Set.of(Permissions.READ));


    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}

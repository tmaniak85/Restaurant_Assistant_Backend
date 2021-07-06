package com.edu.assistant.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {

    ADMIN,
    WAITER,
    CHEF;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

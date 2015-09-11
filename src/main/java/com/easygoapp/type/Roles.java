package com.easygoapp.type;

/**
 * Created by SCJP on 05.03.2015.
 */
public enum Roles {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    Roles(String prefix) {
        this.role = prefix;
    }

    public String getRole() {
        return role;
    }
}

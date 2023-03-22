package com.example.neolabs.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ADMIN"),
    ROLE_MANAGER("MANAGER");

    final String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}

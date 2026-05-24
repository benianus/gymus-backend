package com.jetbrains.shared.enums;

import lombok.Getter;

@Getter
public enum Role {
    EMPLOYEE("EMPLOYEE"),
    MEMBER("MEMBER"),
    OWNER("OWNER");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}

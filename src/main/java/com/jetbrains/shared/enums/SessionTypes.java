package com.jetbrains.shared.enums;

import lombok.Getter;

@Getter
public enum SessionTypes {
    BODY_BUILDING("Body building"),
    UNLIMITED_CARDIO("Cardio"),
    BODY_BUILDING_UNLIMITED_CARDIO("Body building + cardio");

    private final String name;

    SessionTypes(String name) {
        this.name = name;
    }
}

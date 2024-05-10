package com.mftplus.automation.model.enums;

import lombok.Getter;

@Getter
public enum Gender {
    male("مرد"),
    female("زن");

    private final String title;

    Gender(String title) {
        this.title = title;
    }
}

package com.mftplus.automation.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    admin("ادمین"),
    manager("مدیر"),
    user("کاربر سیستم");

    private final String title;

    Role(String title) {
        this.title = title;
    }
}

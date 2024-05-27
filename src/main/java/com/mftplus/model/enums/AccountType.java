package com.mftplus.model.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    savings("پس انداز"),
    current("جاری");

    private final String title;

    AccountType(String title) {
        this.title = title;
    }
}

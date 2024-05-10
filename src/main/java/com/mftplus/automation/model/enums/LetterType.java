package com.mftplus.automation.model.enums;

import lombok.Getter;

@Getter
public enum LetterType {
    inSystem("ورودی"),
    receiving("دریافتی"),
    sending("ارسالی");

    public final String title;

    LetterType(String title) {
        this.title = title;
    }
}

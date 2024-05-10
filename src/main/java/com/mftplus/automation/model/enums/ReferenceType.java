package com.mftplus.automation.model.enums;

import lombok.Getter;

@Getter
public enum ReferenceType {
    estehzar("استحضار"),
    etela("اطلاع"),
    eghdam("اقدام"),
    pasokh("پاسخ");

    public final String title;

    ReferenceType(String title) {
        this.title = title;
    }
}

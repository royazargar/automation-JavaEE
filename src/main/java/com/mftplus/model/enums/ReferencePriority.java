package com.mftplus.model.enums;

import lombok.Getter;

@Getter
public enum ReferencePriority {
    normal("عادی"),
    urgent("فوری");

    public final String title;

    ReferencePriority(String title) {
        this.title = title;
    }
}

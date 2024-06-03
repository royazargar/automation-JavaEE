package com.mftplus.model.enums;

public enum PaymentType {
    cashPayment("نقد"),
    cardPayment("واریز به بانک"),
    cardAndCash("کارت و نقد"),
    checkPayment("چک");

    private String title;

    PaymentType(String title) {
        this.title = title;
    }
}

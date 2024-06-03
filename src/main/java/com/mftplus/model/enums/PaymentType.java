package com.mftplus.model.enums;

public enum PaymentType {
    cashPayment("نقد"),
    cardPayment("واریز به بانک"),
    checkPayment("چک"),
    cardAndCash("کارت و نقد"),
    cardAndCheck("کارت و چک"),
    cashAndCheck("نقد و چک"),
    cardAndCashAndCheck("کارت و نقد و چک");

    private String title;

    PaymentType(String title) {
        this.title = title;
    }
}

package com.company.module;

public class Card {
    private String value;
    private String cardSign;

    public Card() {
        this.value = "*";
        this.cardSign = "*";
    }

    public Card(String value, String cardSign) {
        this.value = value;
        this.cardSign = cardSign;
    }

    @Override
    public String toString() {
        return value+" of "+cardSign;
    }
}

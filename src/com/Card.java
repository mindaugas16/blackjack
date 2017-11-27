package com;

public class Card {
    private Value value;
    private Suit suit;
    private int points = 0;

    public Card() {
        this.value = null;
        this.suit = null;
    }

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        this.points = value.getValue();
    }

    public int getPoints() {
        return points;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return value +" of " + suit;
    }
}

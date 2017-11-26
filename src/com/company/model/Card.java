package com.company.model;

public class Card {
    private Value value;
    private Suit suit;
    private int points;

    public Card() {
        this.value = null;
        this.suit = null;
        this.points = 0;
    }

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        this.points = cardPoints(value);
    }

    private int cardPoints(Value value){
        int cardPoints=0;
        switch (value) {
            case TWO:
                cardPoints = 2;
                break;
            case THREE:
                cardPoints = 3;
                break;
            case FOUR:
                cardPoints = 4;
                break;
            case FIVE:
                cardPoints = 5;
                break;
            case SIX:
                cardPoints = 6;
                break;
            case SEVEN:
                cardPoints = 7;
                break;
            case EIGHT:
                cardPoints = 8;
                break;
            case NINE:
                cardPoints = 9;
                break;
            case TEN:
                cardPoints = 10;
                break;
            case JACK:
                cardPoints = 10;
                break;
            case QUEEN:
                cardPoints = 10;
                break;
            case KING:
                cardPoints = 10;
                break;
            case ACE:
                cardPoints = 1;
                break;
        }
        return cardPoints;
    }

    public Value getValue() {
        return value;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return value+" of "+suit;
    }
}

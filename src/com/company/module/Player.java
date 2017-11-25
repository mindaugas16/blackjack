package com.company.module;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardsInHands = new ArrayList<>();

    public Player() {
    }

    public void setCardsInHands(ArrayList<Card> cardsInHands) {
        this.cardsInHands = cardsInHands;
    }

    public ArrayList<Card> getCardsInHands() {
        return cardsInHands;
    }

    public void addCardToHands(Card card){
        this.cardsInHands.add(card);
    }
}

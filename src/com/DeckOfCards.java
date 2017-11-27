package com;

import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {
    private List<Card> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                this.deck.add(new Card(value, suit));
            }
        }
    }
    public void shuffle() {
        List<Card> temp = new ArrayList<>();
        while (!deck.isEmpty()) {
            int loc = (int) (Math.random() * deck.size());
            temp.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = temp;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}

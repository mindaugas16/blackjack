package com.company.module;

import java.util.ArrayList;

public class DeckOfCards {
    private ArrayList<Card> deck = new ArrayList<Card>();

    public DeckOfCards() {
        String[] values = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        String[] signs = {"Spades","Hearts","Clubs","Diamonds"};
        for (String sign : signs) {
            for (String value:values) {
                this.deck.add(new Card(value, sign));
            }
        }
    }
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        while (!deck.isEmpty()) {
            int loc = (int) (Math.random() * deck.size());
            temp.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = temp;
    }
    public void showDeck(){
        int i=1;
        for(Card card:deck){
            System.out.println("Card: "+ i++ +" " +card);
        }
    }
    public ArrayList<Card> giveCards(int n){
        ArrayList<Card> tempDeck = new ArrayList<>();
        for(int i=0;i<n;i++) {
            Card temp = deck.get(0);
            tempDeck.add(temp);
            deck.remove(0);
        }
        return tempDeck;
    }

    public Card giveCard(){
        Card temp = deck.get(0);
        deck.remove(0);

        return temp;
    }


    @Override
    public String toString() {
        return deck+"";
    }
}

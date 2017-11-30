package com.company;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards{
    private List<Card> deck;
    private Task task;

    public DeckOfCards() {
        deck = new ArrayList<>();
        task = new Task() {
            @Override
            protected Object call() throws Exception {
                for (Suit suit : Suit.values()) {
                    for (Value value : Value.values()) {
                        deck.add(new Card(value, suit));
                    }
                }
                System.out.println("Created");
                return deck;
            }
        };
        new Thread(task).start();
    }

//    public DeckOfCards() {
//        deck = new ArrayList<>();
//        for (Suit suit : Suit.values()) {
//            for (Value value : Value.values()) {
//                deck.add(new Card(value, suit));
//                System.out.println(deck.size());
//            }
//        }
//    }


    public Task<List<Card>> getTask() {
        return task;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void moveBackToDeck(){

    }

}

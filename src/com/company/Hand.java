package com.company;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Hand{
    private ObservableList<Node> cards;
    private int credits = 0;
    private int aces = 0;
    private SimpleIntegerProperty score = new SimpleIntegerProperty(0);

    public Hand(ObservableList<Node> cards,int credits) {
        this.cards = cards;
        this.credits = credits;
    }

    public Hand(ObservableList<Node> cards) {
        this.cards = cards;
    }

    public void takeCard(Card card){
        cards.add(card);
        if (card.getValue().equals(Value.ACE)) {
            aces++;
        }
        if(score.get() + card.getPoints() > 21 && aces != 0){
            score.set(score.get() + 1);
            aces--;
        }else{
            score.set(score.get() + card.getPoints());
        }
    }

    public ObservableList<Node> getCards() {
        return cards;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public void setScore(int score) {
        this.score.set(score);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void showCards(){
        for(Node card:cards) {
            System.out.println("- "+card);
        }
    }

    public void reset(){
        cards.clear();
        score.set(0);
        aces = 0;
    }
}

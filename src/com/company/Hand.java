package com.company;

import com.company.creditsStrategy.CreditsBehavior;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public abstract class Hand{
    private ObservableList<Node> cards;
    private SimpleIntegerProperty score = new SimpleIntegerProperty(0);

    private int aces = 0;

    public CreditsBehavior creditsBehavior;

    public Hand(ObservableList<Node> cards) {
        this.cards = cards;
    }

    public ObservableList<Node> getCards() {
        return cards;
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

    public void takeCard(Card card){
        getCards().add(card);
        if (card.getValue().equals(Value.ACE)) {
            aces++;
        }
        if(getScore() + card.getPoints() > 21 && aces != 0){
            setScore(getScore() + 1);
            aces--;
        }else{
            setScore(getScore() + card.getPoints());
        }
    }

    public void reset(){
        cards.clear();
        score.set(0);
        aces = 0;
    }

}

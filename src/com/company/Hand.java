package com.company;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private int credits = 0;
    private int score = 0;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(int credits) {
        cards = new ArrayList<>();
        this.credits = credits;
    }

    public void takeCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int countScore(){
        int score = 0;
        for (Card card : getCards()) {
            if (this.score > 10 && card.getValue().equals(Value.ACE)) {
                score += 1;
            } else {
                score += card.getPoints();
            }
        }
        setScore(score);
        return score;
    }

    public int getScore() {
        return countScore();
    }
    public void showCards(){
        for(Card card:cards) {
            System.out.println("- "+card);
        }
    }
}

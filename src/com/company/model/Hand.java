package com.company.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Hand{
    private ArrayList<Card> cardsInHands = new ArrayList<>();
    private SimpleIntegerProperty score = new SimpleIntegerProperty(0);
    private VBox root;
    private SimpleIntegerProperty money = new SimpleIntegerProperty(250);

    public Hand(VBox root) {
        this.root = root;
    }

    public int getMoney() {
        return money.get();
    }

    public ArrayList<Card> getCardsInHands() {
        return cardsInHands;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public void placeBet(int bet){
        money.set(money.get()-bet);
    }

    public SimpleIntegerProperty moneyProperty() {
        return money;
    }

    public void takeCard(Card card){
        cardsInHands.add(card);
        score.set(score.get()+card.getPoints());
        Label label = new Label(card.toString());
        root.getChildren().add(label);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}

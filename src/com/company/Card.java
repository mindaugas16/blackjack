package com.company;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends Parent{
    private Label label;
    private Value value;
    private Suit suit;
    private int points = 0;

    public Card() {
        value = null;
        suit = null;
        label = generateCardView();
    }

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        points = value.getValue();
        label = generateCardView();
    }

    private Label generateCardView() {
        Image image;
        double wCard = 120;
        double hCard = wCard/0.75;
        String cap = "red_back";

        if (suit != null && value != null) {
            String str = value.toString();
            cap = str.substring(0, 1) + str.substring(1).toLowerCase()+ suit.toString().charAt(0);
        }
        image = new Image(getClass().getResourceAsStream("cards/"+cap+".png"), wCard, hCard, true, true);

        Label label = new Label();
        label.setId("card");
        label.setPrefSize(wCard, hCard);
        label.setGraphic(new ImageView(image));
        getChildren().add(label);

        return label;
    }

    public int getPoints() {
        return points;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value +" of " + suit;
    }
}

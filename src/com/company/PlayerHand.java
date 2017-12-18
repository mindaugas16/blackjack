package com.company;

import com.company.creditsStrategy.EnableCredits;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class PlayerHand extends Hand {
    public PlayerHand(ObservableList<Node> cards) {
        super(cards);
        creditsBehavior = new EnableCredits();
    }
}

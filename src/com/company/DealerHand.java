package com.company;

import com.company.creditsStrategy.DisableCredits;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class DealerHand extends Hand {
    public DealerHand(ObservableList<Node> cards) {
        super(cards);
        creditsBehavior = new DisableCredits();
    }
}

package com.company.creditsStrategy;


import javafx.beans.property.SimpleIntegerProperty;

public class DisableCredits implements CreditsBehavior {
    public DisableCredits() {
    }

    public int getCredits() {
        return 0;
    }

    public void setCredits(int credits) {
    }

    public SimpleIntegerProperty creditsProperty() {
        return null;
    }
}

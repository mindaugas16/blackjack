package com.company.creditsStrategy;

import javafx.beans.property.SimpleIntegerProperty;

public class EnableCredits implements CreditsBehavior {
    private SimpleIntegerProperty credits = new SimpleIntegerProperty(0);

    public EnableCredits() {
        setCredits(250);
    }

    public SimpleIntegerProperty creditsProperty() {
        return credits;
    }

    public int getCredits() {
        return credits.get();
    }

    public void setCredits(int credits) {
        this.credits.set(credits);
    }
}

package com.company.creditsStrategy;

import javafx.beans.property.SimpleIntegerProperty;

public interface CreditsBehavior {
    int getCredits();
    void setCredits(int credits);
    SimpleIntegerProperty creditsProperty();
}
package com.company.chipFactory;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public abstract class Chip extends Parent{
    private Label chip;
    private int chipValue;

    public Label getChip() {
        return chip;
    }

    public void setChip(Label chip) {
        this.chip = chip;
        getChildren().add(chip);
    }

    public void setChipValue(int chipValue) {
        this.chipValue = chipValue;
    }

    public int getChipValue() {
        return chipValue;
    }
}

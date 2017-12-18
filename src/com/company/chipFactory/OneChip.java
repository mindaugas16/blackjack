package com.company.chipFactory;

import javafx.scene.control.Label;

public class OneChip extends Chip {

    public OneChip() {
        Label chip = new Label("1");
        chip.setId("chip");
        chip.getStyleClass().add("oneChip");
        setChipValue(1);
        setChip(chip);
    }

}

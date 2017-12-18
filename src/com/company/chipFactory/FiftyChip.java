package com.company.chipFactory;

import javafx.scene.control.Label;

public class FiftyChip extends Chip {
    public FiftyChip() {
        Label chip = new Label("50");
        chip.setId("chip");

        setChipValue(50);
        setChip(chip);
    }
}

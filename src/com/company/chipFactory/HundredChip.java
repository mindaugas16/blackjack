package com.company.chipFactory;

import javafx.scene.control.Label;

public class HundredChip extends Chip{
    public HundredChip() {
        Label chip = new Label("100");
        chip.setId("chip");

        setChipValue(100);
        setChip(chip);
    }
}

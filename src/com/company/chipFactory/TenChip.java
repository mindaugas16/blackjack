package com.company.chipFactory;

import javafx.scene.control.Label;

public class TenChip extends Chip{

    public TenChip() {
        Label chip = new Label("10");
        chip.setId("chip");

        setChipValue(10);
        setChip(chip);
    }
}

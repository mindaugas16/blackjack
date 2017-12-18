package com.company.chipFactory;

import com.company.Hand;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;


public class ChipFactory {
    private Hand player;
    private SimpleBooleanProperty playing;
    private SimpleIntegerProperty betValue;
    private List<Chip> placedChips = new ArrayList<>();
    private Label betValueLabel;

    public Chip makeChip(int chipIndex, Hand player, SimpleBooleanProperty playing, SimpleIntegerProperty betValue, Label betValueLabel){
        this.player = player;
        this.playing = playing;
        this.betValue = betValue;
        this.betValueLabel = betValueLabel;

        Chip temp = null;

        switch (chipIndex){
            case 1:
                temp = new OneChip();
                break;
            case 2:
                temp = new TenChip();
                break;
            case 3:
                temp =  new FiftyChip();
                break;
            case 4:
                temp = new HundredChip();
                break;
        }
        execute(temp);
        return temp;
    }

    public void execute(Chip chip){
        chip.getChip().disableProperty().bind(playing);

        chip.getChip().visibleProperty().bind(new BooleanBinding() {
            {
                bind(player.creditsBehavior.creditsProperty());
            }
            @Override
            protected boolean computeValue() {
                return !(chip.getChipValue() > player.creditsBehavior.creditsProperty().getValue());
            }
        });
        chip.getChip().setOnMouseClicked(event1 -> {
            placedChips.add(chip);
            betValue.setValue(betValue.getValue()+ chip.getChipValue());
            player.creditsBehavior.creditsProperty().setValue(player.creditsBehavior.creditsProperty().getValue()- chip.getChipValue());
        });

        betValueLabel.setOnMouseClicked(event2 -> {
            if(!placedChips.isEmpty()) {
                Chip temp = placedChips.get(0);
                betValue.setValue(betValue.getValue() - temp.getChipValue());
                player.creditsBehavior.creditsProperty().setValue(player.creditsBehavior.creditsProperty().getValue() + temp.getChipValue());
                placedChips.remove(0);
            }
        });
    }
}

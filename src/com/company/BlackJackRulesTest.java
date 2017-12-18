package com.company;

import javafx.scene.layout.HBox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackJackRulesTest {
    private Hand player;
    private Hand dealer;
    private BlackJackRules rules;

    @Before
    public void setup(){
        player = new PlayerHand(new HBox().getChildren());
        dealer = new DealerHand(new HBox().getChildren());
        rules = new BlackJackRules(player,dealer);

        System.out.println("Test is running..");
    }

    @Test
    public void bet() throws Exception{
        rules.bet(50);
        assertEquals(200,player.creditsBehavior.getCredits());
    }
}
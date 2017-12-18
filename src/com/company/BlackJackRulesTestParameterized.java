package com.company;

import javafx.scene.layout.HBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackJackRulesTestParameterized {

    private Hand player;
    private Hand dealer;
    private BlackJackRules rules;

    private int playerScore;
    private int dealerScore;
    private String expected;

    public BlackJackRulesTestParameterized(int playerScore, String expected,int dealerScore) {
        this.playerScore = playerScore;
        this.dealerScore = dealerScore;
        this.expected = expected;
    }

    @Before
    public void setup(){
        player = new PlayerHand(new HBox().getChildren());
        dealer = new DealerHand(new HBox().getChildren());
        rules = new BlackJackRules(player,dealer);

        System.out.println("Test is running..");
    }

    @Test
    public void whoWins() throws Exception {
        player.setScore(playerScore);
        dealer.setScore(dealerScore);
        assertEquals(expected,rules.whoWins());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {22,"Dealer wins",18},
                {17,"You win",16},
                {18,"You win",17},
                {15,"Dealer wins",20},
                {20,"You win",19},
                {21,"You win",20},
                {19,"You win",22},
                {21,"You win",23},
                {20,"Push",20},
                {21,"Push",21}
        });

    }


}
package com.company;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;

public class BlackJackRules implements BlackJackRulesInterface {
    private Hand player;
    private Hand dealer;
    private DeckOfCards deck;
    private SimpleBooleanProperty playing = new SimpleBooleanProperty(false);
    private SimpleIntegerProperty betValue = new SimpleIntegerProperty(0);

    public BlackJackRules(Hand player, Hand dealer) {
        this.player = player;
        this.dealer = dealer;
        this.deck = DeckOfCards.getInstance();
    }

    public void prepareTable() {
        if(!player.getCards().isEmpty() && !dealer.getCards().isEmpty()) {
            putBackToDeck(player);
            putBackToDeck(dealer);
        }
    }

    public void gameOver() {
        System.out.println("Game over");
    }

    public void bet(int betValue) {
        player.creditsBehavior.setCredits(player.creditsBehavior.getCredits()-betValue);
    }

    public void stand() {
        dealer.getCards().remove(1);
        giveCard(dealer);

        int myScore = player.getScore();
        int bankScore = dealer.getScore();

        if (myScore < 21) {
            while (bankScore < 17) {
                giveCard(dealer);
                bankScore = dealer.getScore();
            }
        }
        if (player.creditsBehavior.getCredits() <= 0) {
            gameOver();
        }
    }

    public String whoWins() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        String message = null;
        if (playerScore == dealerScore && dealerScore <= 21 && playerScore <= 21) {
            message = "Push";
        } else if (playerScore == 21 || dealerScore > 21 || (playerScore > dealerScore && playerScore <= 21)) {
            message = "You win";
            win(betValue.getValue());
        } else if (dealerScore == 21 || playerScore > 21 || (playerScore < dealerScore && dealerScore <= 21)) {
            message = "Dealer wins";
        }
        betValue.set(0);
        return message;
    }

    public void win(int betValue) {
        Card temp1 = (Card)player.getCards().get(0);
        Card temp2 = (Card)player.getCards().get(1);

        int firstCard = temp1.getPoints();
        int secondCard = temp2.getPoints();

        if ((firstCard + secondCard) == 21) {
            player.creditsBehavior.setCredits(player.creditsBehavior.getCredits() + (int) (betValue * 2.5));
        } else {
            player.creditsBehavior.setCredits(player.creditsBehavior.getCredits() + betValue * 2);
        }
    }

    public void deal() {
        deck.shuffle();

        giveCard(player);
        giveCard(player);
        giveCard(dealer);
        dealer.getCards().add(new Card());
    }

    public void giveCard(Hand player) {
        player.takeCard(deck.getDeck().get(0));
        deck.getDeck().remove(0);
    }

    public void putBackToDeck(Hand player){
        for(Node card:player.getCards()){
            deck.getDeck().add((Card)card);
        }
        player.reset();
    }

    public DeckOfCards getDeck() {
        return deck;
    }


    public void setPlaying(boolean playing) {
        this.playing.set(playing);
    }

    public SimpleBooleanProperty playingProperty() {
        return playing;
    }


    public SimpleIntegerProperty betValueProperty() {
        return betValue;
    }
}

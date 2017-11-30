package com.company;


import javafx.scene.Node;

public class BlackJackRules implements BlackJackRulesInterface {
    private Hand player;
    private Hand dealer;
    private DeckOfCards deck;

    public BlackJackRules(Hand player, Hand dealer) {
        this.player = player;
        this.dealer = dealer;
        deck = new DeckOfCards();
    }

    public void prepareTable() {
        if(!player.getCards().isEmpty() && !dealer.getCards().isEmpty()) {
            putBackToDeck(player);
            putBackToDeck(dealer);

            player.getCards().clear();
            player.setScore(0);
            dealer.setScore(0);
            dealer.getCards().clear();
        }
    }

    public void gameOver() {
        System.out.println("Game over");
        System.exit(0);
    }

    public int bet(int betValue, int myCredits) {
        myCredits = myCredits - betValue;
        return myCredits;
    }

    public void stand(int betValue) {
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
        if (player.getCredits() <= 0) {
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
//            win(betValue);
        } else if (dealerScore == 21 || playerScore > 21 || (playerScore < dealerScore && dealerScore <= 21)) {
            message = "Dealer wins";
        }
        return message;
    }

    public int win(int betValue) {
        int firstCard = 0;
        int secondCard = 0;

        if ((firstCard + secondCard) == 21) {
            player.setCredits(player.getCredits() + (int) (betValue * 2.5));
        } else {
            player.setCredits(player.getCredits() + betValue * 2);
        }

        return player.getCredits();
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
    }


    public void playersCards() {
        System.out.println("Player cards (" + player.getScore() + ")");
        player.showCards();
        System.out.println("\n" + "Dealer cards (" + dealer.getScore() + ")");
        dealer.showCards();
    }

    public DeckOfCards getDeck() {
        return deck;
    }
}

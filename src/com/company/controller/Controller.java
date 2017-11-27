package com.company.controller;

import com.company.model.Card;
import com.company.model.DeckOfCards;
import com.company.model.Hand;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Controller {
    private Hand player;
    private Hand dealer;
    private DeckOfCards deck;

    private int placedBet;

    @FXML
    private VBox dealerCards, playerCards;
    @FXML
    private Label playerScore,dealerScore,placeYourBets;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button hitButton,standButton,dealButton;
    @FXML
    private TextField playerBetField;

    @FXML
    private GridPane gridPane,gridPaneLeft,gridPaneTop;

    @FXML
    private void initialize(){
        player = new Hand(playerCards);
        dealer = new Hand(dealerCards);

        player.scoreProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                stand();
            }
        });

        dealer.scoreProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                stand();
            }
        });
    }

    @FXML
    private void playAction() {
        gridPane.setVisible(false);
        playerCards.getChildren().clear();
        dealerCards.getChildren().clear();
        player.setScore(0);
        dealer.setScore(0);

        gridPaneLeft.setVisible(true);

        placeYourBets.textProperty().bind(new SimpleStringProperty("Place Your Bets: €").concat(player.moneyProperty().asString()));
        deck = new DeckOfCards();
        deck.shuffle();

        dealButton.disableProperty().bind(playerBetField.textProperty().isEmpty());

    }
    @FXML
    private void dealAction(){
        placedBet = Integer.parseInt(playerBetField.getText());
        player.placeBet(placedBet);

        playerBetField.clear();

        gridPane.setVisible(true);
        hitButton.setVisible(true);
        standButton.setVisible(true);

        gridPaneLeft.setVisible(false);
        gridPaneTop.setVisible(false);

        player.takeCard(deck.giveCard());
        dealer.takeCard(deck.giveCard());
        player.takeCard(deck.giveCard());
        dealer.takeCard(new Card());

        playerScore.textProperty().bind(new SimpleStringProperty("Player: ").concat(player.scoreProperty().asString()));
        dealerScore.textProperty().bind(new SimpleStringProperty("Dealer: ").concat(dealer.scoreProperty().asString()));
    }

    @FXML
    private void hit(){
        player.takeCard(deck.giveCard());
    }

    @FXML
    private void stand(){
        hitButton.setVisible(false);
        standButton.setVisible(false);


            dealer.getCardsInHands().remove(1);
            dealerCards.getChildren().remove(1);
            dealer.takeCard(deck.giveCard());

        if(player.scoreProperty().get() < 21) {
            while (dealer.scoreProperty().get() < 17) {
                dealer.takeCard(deck.giveCard());
            }
        }

        mainPane.setBottom(new Label(winner()));
        gridPaneTop.setVisible(true);
    }

    public String winner(){
        String message = null;
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (playerScore == dealerScore && dealerScore <= 21 && playerScore <= 21) {
            message = "Push";
        } else if (playerScore == 21 || dealerScore > 21 || (playerScore > dealerScore && playerScore <= 21)) {
            message = "You win";
            player.moneyProperty().set(player.getMoney()+placedBet*2);
        } else if (dealerScore == 21 || playerScore > 21 || (playerScore < dealerScore && dealerScore <= 21)) {
            message = "Bank wins";
        }
        return message;
    }
}


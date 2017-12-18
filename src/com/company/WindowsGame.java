package com.company;

import com.company.chipFactory.Chip;
import com.company.chipFactory.ChipFactory;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowsGame extends Application implements Game {

    private BlackJackRules rules;
    private Hand player;
    private Hand dealer;

    @FXML
    private HBox playerCards, dealerCards;
    @FXML
    private VBox chips;
    @FXML
    private Label playerScore, dealerScore, message, playerCredits, betValueLabel;
    @FXML
    private Button dealButton, hitButton, standButton, playButton;


    public WindowsGame() {
    }

    @FXML
    private void playAction() {
        playButton.setDisable(true);
        player = new PlayerHand(playerCards.getChildren());
        dealer = new DealerHand(dealerCards.getChildren());
        rules = new BlackJackRules(player, dealer);
        contentInitialize();
    }

    @FXML
    public void dealAction() {
        rules.setPlaying(true);
        rules.prepareTable();
        resetTable();
//        rules.bet(betValue.getValue());

        rules.deal();
        if (player.scoreProperty().getValue() == 21) {
            standAction();
        }
    }

    @FXML
    private void hitAction() {
        rules.setPlaying(true);
        rules.giveCard(player);
        if (player.scoreProperty().get() >= 21) {
            standAction();
        }
    }

    @FXML
    private void standAction() {
        rules.setPlaying(false);
        rules.stand();
        message.setText(rules.whoWins());
    }

    private void resetTable() {
        message.setText(null);
        player.reset();
        dealer.reset();
        playerCards.getChildren().clear();
        dealerCards.getChildren().clear();
    }

    private void contentInitialize() {
        rules.getDeck().getTask().setOnRunning(event -> {
            message.setText("Shuffling...");
        });

        rules.getDeck().getTask().setOnSucceeded(event -> {
            message.setText("");

            dealButton.disableProperty().bind(rules.playingProperty().or(rules.betValueProperty().isEqualTo(0)));
            betValueLabel.disableProperty().bind(rules.playingProperty());
            hitButton.disableProperty().bind(rules.playingProperty().not());
            standButton.disableProperty().bind(rules.playingProperty().not());

            playerScore.textProperty().bind(new SimpleStringProperty("PlayerHand score: ").concat(player.scoreProperty().asString()));
            dealerScore.textProperty().bind(new SimpleStringProperty("Dealer score: ").concat(dealer.scoreProperty().asString()));


            playerCredits.textProperty().bind(new SimpleStringProperty("$").concat(player.creditsBehavior.creditsProperty().asString()));
            betValueLabel.textProperty().bind(new SimpleStringProperty("$").concat(rules.betValueProperty()));

            ChipFactory chipFactory = new ChipFactory();
            Chip newChip = null;

            for (int i = 1; i <= 4; i++) {
                newChip = chipFactory.makeChip(i, player, rules.playingProperty(), rules.betValueProperty(), betValueLabel);
                chips.getChildren().add(newChip.getChip());
            }
        });
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startView.fxml"));
        Pane root = loader.load();
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Black Jack");
        primaryStage.show();
    }

    @Override
    public void run() throws Exception {
        launch();
    }

}

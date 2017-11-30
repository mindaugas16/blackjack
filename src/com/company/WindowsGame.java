package com.company;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowsGame extends Application implements Game{

    private BlackJackRules rules;
    private Hand player;
    private Hand dealer;
    private SimpleBooleanProperty playing = new SimpleBooleanProperty(false);
    private SimpleIntegerProperty betValue = new SimpleIntegerProperty(0);

    @FXML
    private HBox playerCards,dealerCards;
    @FXML
    private Label playerScore, dealerScore,message,playerCredits,betValueLabel,oneDollar,tenDollar,fiftyDollar,hundredDollar;
    @FXML
    private Button dealButton,hitButton,standButton,playButton;


    public WindowsGame() {
    }

    @FXML
    private void playAction(){
        playButton.setDisable(true);
        player = new Hand(playerCards.getChildren(),new SimpleIntegerProperty(250));
        dealer = new Hand(dealerCards.getChildren());
        rules = new BlackJackRules(player, dealer);

        rules.getDeck().getTask().setOnRunning(event -> {
            message.setText("Shuffling...");
        });

        rules.getDeck().getTask().setOnSucceeded(event -> {
            message.setText("");

            dealButton.disableProperty().bind(playing);
            hitButton.disableProperty().bind(playing.not());
            standButton.disableProperty().bind(playing.not());

            playerCredits.textProperty().bind(new SimpleStringProperty("$").concat(player.creditsProperty().asString()));
            betValueLabel.textProperty().bind(new SimpleStringProperty("$").concat(betValue));

            oneDollar.disableProperty().bind(new BooleanBinding() {
                {
                    bind(player.creditsProperty());
                }
                @Override
                protected boolean computeValue() {
                    return 1>player.creditsProperty().getValue() || playing.getValue();
                }
            });
            tenDollar.disableProperty().bind(new BooleanBinding() {
                {
                    bind(player.creditsProperty());
                }
                @Override
                protected boolean computeValue() {
                    return 10>player.creditsProperty().getValue() || playing.getValue();
                }
            });
            fiftyDollar.disableProperty().bind(new BooleanBinding() {
                {
                    bind(player.creditsProperty());
                }
                @Override
                protected boolean computeValue() {
                    return 50>player.creditsProperty().getValue() || playing.getValue();
                }
            });
            hundredDollar.disableProperty().bind(new BooleanBinding() {
                {
                    bind(player.creditsProperty());
                }
                @Override
                protected boolean computeValue() {
                    return 100>player.creditsProperty().getValue() || playing.getValue();
                }
            });

            oneDollar.setOnMouseClicked(event1 -> {
                betValue.setValue(betValue.getValue()+1);
//                player.creditsProperty().setValue(player.creditsProperty().getValue()-1);
            });
            tenDollar.setOnMouseClicked(event1 -> {
                betValue.setValue(betValue.getValue()+10);
//                player.creditsProperty().setValue(player.creditsProperty().getValue()-10);
            });
            fiftyDollar.setOnMouseClicked(event1 -> {
                betValue.setValue(betValue.getValue()+50);
//                player.creditsProperty().setValue(player.creditsProperty().getValue()-50);
            });
            hundredDollar.setOnMouseClicked(event1 -> {
                betValue.setValue(betValue.getValue()+100);
//                player.creditsProperty().setValue(player.creditsProperty().getValue()-100);
            });
        });
    }

    @FXML
    public void dealAction() {
        playing.set(true);
        rules.prepareTable();
        resetTable();
        rules.bet(betValue.getValue());

        playerScore.textProperty().bind(new SimpleStringProperty("Player score: ").concat(player.scoreProperty().asString()));
        dealerScore.textProperty().bind(new SimpleStringProperty("Dealer score: ").concat(dealer.scoreProperty().asString()));

        rules.deal();
        if(player.scoreProperty().getValue() == 21){
            standAction();
        }
    }

    @FXML
    private void hitAction(){
        playing.set(true);
        rules.giveCard(player);
        if(player.scoreProperty().get() >= 21){
            standAction();
        }
    }

    @FXML
    private void standAction(){
        playing.set(false);
        rules.stand();
        message.setText(rules.whoWins(betValue.getValue()));
    }

    private void resetTable(){
        message.setText(null);
        player.reset();
        dealer.reset();
        playerCards.getChildren().clear();
        dealerCards.getChildren().clear();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
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

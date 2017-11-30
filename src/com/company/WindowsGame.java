package com.company;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
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
    private SimpleBooleanProperty status = new SimpleBooleanProperty(false);

    @FXML
    private HBox playerCards,dealerCards;
    @FXML
    private Label playerScore, dealerScore;
    @FXML
    private Button dealButton,hitButton,standButton;
    @FXML
    private Label message;

    public WindowsGame() {
    }

    @FXML
    private void initialize(){
        player = new Hand(playerCards.getChildren(),250);
        dealer = new Hand(dealerCards.getChildren());
        rules = new BlackJackRules(player, dealer);

        dealButton.disableProperty().bind(status);
        hitButton.disableProperty().bind(status.not());
        standButton.disableProperty().bind(status.not());
    }

    @FXML
    public void dealAction() {
        status.set(true);
        rules.prepareTable();
        resetTable();

//        rules.getDeck().getTask().setOnRunning(event -> {
//            status.set(false);
//            message.setText("Shuffling...");
//        });
//        rules.getDeck().getTask().setOnSucceeded(event -> {
//            status.set(true);
//            message.setText("");
//
//        });
        playerScore.textProperty().bind(new SimpleStringProperty("Player score: ").concat(player.scoreProperty().asString()));
        dealerScore.textProperty().bind(new SimpleStringProperty("Dealer score: ").concat(dealer.scoreProperty().asString()));
        rules.deal();
    }

    @FXML
    private void hitAction(){
        status.set(true);
        rules.giveCard(player);
        if(player.scoreProperty().get() >= 21){
            standAction();
        }
    }

    @FXML
    private void standAction(){
        status.set(false);
        rules.stand(0);
        message.setText(rules.whoWins());
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
//        loader.setController(this);
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

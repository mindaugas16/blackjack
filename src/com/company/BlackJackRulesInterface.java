package com.company;

public interface BlackJackRulesInterface {
    void prepareTable();
    void bet(int betValue);
    void stand();
    String whoWins(int betValue);
    void win(int betValue);
    void deal();
    void giveCard(Hand player);
    void playersCards();
    void gameOver();
}

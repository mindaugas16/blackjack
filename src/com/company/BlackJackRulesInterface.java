package com.company;

public interface BlackJackRulesInterface {
    void prepareTable();
    int bet(int betValue, int myCredits);
    void stand(int betValue);
    void whoWins(int playerScore,int dealerScore,int betValue);
    int win(int betValue);
    void deal();
    void giveCard(Hand player);
    void playersCards();
    void gameOver();
}

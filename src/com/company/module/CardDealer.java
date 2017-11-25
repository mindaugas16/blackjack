package com.company.module;

public class CardDealer {
    private DeckOfCards deckOfCards;
    private MePlayer mePlayer;
    private BankPlayer bankPlayer;

    public CardDealer(DeckOfCards deckOfCards, MePlayer mePlayer, BankPlayer bankPlayer) {
        this.deckOfCards = deckOfCards;
        this.mePlayer = mePlayer;
        this.bankPlayer = bankPlayer;
    }

    public void deal(){
        deckOfCards.shuffle();

        mePlayer.setCardsInHands(deckOfCards.giveCards(2));
        bankPlayer.addCardToHands(deckOfCards.giveCard());
        bankPlayer.addCardToHands(new Card());
    }


}

package com.company.model;

public class Rules {
    private DeckOfCards deckOfCards;
    private Hand player;
    private Hand dealer;

    public Rules() {
//        this.player = new Hand();
//        this.dealer = new Hand();
    }

    public Hand getPlayer() {
        return player;
    }

    public Hand getDealer() {
        return dealer;
    }

    public void deal(){
        this.deckOfCards = new DeckOfCards();
        deckOfCards.shuffle();

//        player.takeCard(deckOfCards.giveCard(),);
//        dealer.takeCard(deckOfCards.giveCard());
    }

    public void hit(){
//        player.takeCard(deckOfCards.giveCard());
////        openBankCard();
//        dealer.takeCard(deckOfCards.giveCard());
    }

    public void stand() {
//        openBankCard();
//        if (player.getScore() <= 21) {
//            while (dealer.getScore() < 16) {
//                dealer.addCardToHands(deckOfCards.giveCard());
//            }
//        }
        showInfo();

        winner(player.getScore(), dealer.getScore(), 10);
    }

    private void openBankCard(){
//        if(dealer.getCardsInHands().get(1) == null){
//            dealer.getCardsInHands().remove(1);
//        }
    }

    public String winner(int scoreMy,int scoreBank,int betValue){
        String message = null;
        if(scoreMy == 21){
            message = "You win";
            win(betValue);
        }else if(scoreBank == 21){
            message = "Bank wins";
        }else if(scoreMy > 21) {
            message = "Bank wins";
        }else if(scoreBank > 21){
            message = "You win";
            win(betValue);
        }else if(scoreMy > scoreBank){
            message = "You win";
            win(betValue);
        }else if(scoreMy < scoreBank){
            message = "Bank wins";
        }else if(scoreMy == scoreBank){
            message = "Push";
        }
        return message;
    }

    private int win(int betValue){

//        int firstCard = cardValue(player.getCardsInHands().get(0),0);
//        int secondCard = cardValue(player.getCardsInHands().get(1),0);

//        if((firstCard + secondCard) == 21){
//            player.setMoney(player.getMoney()+betValue*2.5);
//        }else {
//            player.setMoney(player.getMoney()+betValue*2);
//        }

        return 0;
    }


    public void showInfo(){


    }

}

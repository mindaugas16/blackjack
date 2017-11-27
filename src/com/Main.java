package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Card> myCards = new ArrayList<>();
    public static List<Card> dealerCards = new ArrayList<>();
    public static int myCredits = 250;
    public static DeckOfCards deck;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int command = 0;
        int betValue = 0;

        while(command != 2) {
            System.out.println("My credits : $"+myCredits);
            System.out.println("1 - Place bets\n" +
                    "2 - Quit");
            command = s.nextInt();
            switch (command){
                case 1:
                    do {
                        System.out.println("Place your bets:");
                        betValue = s.nextInt();
                    }while(betValue > myCredits || betValue == 0);
                    myCredits = bet(betValue,myCredits);
                    System.out.println( "Bet : $"+betValue+"\n");
                    deal();
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Command not exist!");
                    break;
            }
            int commandInner = 0;
            while (commandInner != 2 && command == 1) {
                if(countScore(myCards) < 21) {
                    playersCards();
                    System.out.println("\n" + "1 - Hit\n" +
                            "2 - Stand");
                    commandInner = s.nextInt();

                    switch (commandInner) {
                        case 1:
                            if (countScore(myCards) >= 21) {
                                commandInner = 2;
                                stand(betValue);
                            } else {
                                giveCard(myCards);
                            }
                            break;
                        case 2:
                            stand(betValue);
                            break;
                        default:
                            System.out.println("Command not exist!");
                            break;
                    }
                }else{
                    stand(betValue);
                    break;
                }
            }

        }
    }

    public static void gameOver(){
        System.out.println("Game over");
        System.exit(0);
    }

    public static int bet(int betValue,int myCredits){
        myCredits = myCredits - betValue;
        return myCredits;
    }

    public static void stand(int betValue){
        dealerCards.remove(1);
        giveCard(dealerCards);
        int myScore = countScore(myCards);
        int bankScore = countScore(dealerCards);

        if(myScore < 21) {
            while (bankScore < 17) {
                giveCard(dealerCards);
                bankScore = countScore(dealerCards);
            }
        }
        playersCards();

        whoWins(myScore,bankScore,betValue);

        myCards.clear();
        dealerCards.clear();
        if(myCredits <= 0){
            gameOver();
        }
    }

    public static void whoWins(int playerScore,int dealerScore,int betValue){
        String message = null;
        if (playerScore == dealerScore && dealerScore <= 21 && playerScore <= 21) {
            message = "Push";
        } else if (playerScore == 21 || dealerScore > 21 || (playerScore > dealerScore && playerScore <= 21)) {
            message = "You win";
            win(betValue);
        } else if (dealerScore == 21 || playerScore > 21 || (playerScore < dealerScore && dealerScore <= 21)) {
            message = "Bank wins";
        }
        System.out.println("\n--> "+message+"\n");
    }
    public static int win(int betValue){
        int firstCard = myCards.get(0).getPoints();
        int secondCard = myCards.get(1).getPoints();

        if((firstCard + secondCard) == 21){
            myCredits += (betValue*2.5);
        }else {
            myCredits += (betValue * 2);
        }

        return myCredits;
    }

    public static int countScore(List<Card> playerCards){
        int score = 0;
        for(int i=0;i<playerCards.size();i++) {
            score += playerCards.get(i).getPoints();
        }
        return score;
    }


    public static void deal(){
        deck = new DeckOfCards();
        deck.shuffle();

        giveCard(myCards);
        giveCard(myCards);
        giveCard(dealerCards);
        dealerCards.add(new Card());
    }


    public static void getCards(List<Card> playerCards){
        for(Card card:playerCards) {
            System.out.println(card);
        }
    }
    public static void giveCard(List<Card> playerCards){
        playerCards.add(deck.getDeck().get(0));
        deck.getDeck().remove(0);
    }
    public static void playersCards(){
        System.out.println("My cards ("+countScore(myCards)+")");
        getCards(myCards);
        System.out.println("\n"+"Bank cards ("+countScore(dealerCards)+")");
        getCards(dealerCards);
    }
}

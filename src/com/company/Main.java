package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<String[]> myCards = new ArrayList<String[]>();
    public static ArrayList<String[]> bankCards = new ArrayList<String[]>();
    public static int myCredits = 250;

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
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Command not exist!");
                    break;
            }
            deal(myCards,2);
            deal(bankCards,1);
            int commandInner = 0;
            while (commandInner != 2 && command == 1) {
                System.out.println("My cards ("+countScore(myCards)+")");
                getCards(myCards);
                System.out.println("Bank cards ("+countScore(bankCards)+")");
                getCards(bankCards);

                System.out.println("\n"+"1 - Hit\n" +
                        "2 - Stand");
                commandInner = s.nextInt();

                switch (commandInner) {
                    case 1:
                        createCard(myCards);
                        if (countScore(myCards) > 21) {
                            commandInner = 2;
                            stand(betValue);
                        }
                        break;
                    case 2:
                        stand(betValue);
                        break;
                    default:
                        System.out.println("Command not exist!");
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
        createCard(bankCards);
        int myScore = countScore(myCards);
        int bankScore = countScore(bankCards);

        if(myScore <= 21) {
            while (bankScore < 16) {
                createCard(bankCards);
                bankScore = countScore(bankCards);
            }
        }
        System.out.println("My cards ("+myScore+")");
        getCards(myCards);
        System.out.println("Bank cards ("+bankScore+")");
        getCards(bankCards);

        Winner(myScore,bankScore,betValue);

        myCards.clear();
        bankCards.clear();
        if(myCredits <= 0){
            gameOver();
        }
    }

    public static void Winner(int scoreMy,int scoreBank,int betValue){
        String message = null;
        if(scoreMy == 21){
            message = "You win";
            win(betValue);
        }else if(scoreBank == 21){
            message = "Dealer wins";
        }else if(scoreMy > 21) {
            message = "Dealer wins";
        }else if(scoreBank > 21){
            message = "You win";
            win(betValue);
        }else if(scoreMy > scoreBank){
            message = "You win";
            win(betValue);
        }else if(scoreMy < scoreBank){
            message = "Dealer wins";
        }else if(scoreMy == scoreBank){
            message = "Push";
        }
        System.out.println(message+"\n");
    }
    public static int win(int betValue){
        int firstCard = cardValue(getCard(0,myCards),0);
        int secondCard = cardValue(getCard(1,myCards),0);

        if((firstCard + secondCard) == 21){
            myCredits += (betValue*2.5);
        }else {
            myCredits += (betValue * 2);
        }

        return myCredits;
    }

    public static int countScore(ArrayList<String[]> playerCards){
        int score = 0;
        for(int i=0;i<playerCards.size();i++) {
            score += cardValue(playerCards.get(i)[0],score);
        }
        return score;
    }

    public static int cardValue(String card,int score){
        int value = 0;
        switch (card) {
            case "J":
                value = 10;
                break;
            case "Q":
                value = 10;
                break;
            case "K":
                value = 10;
                break;
            case "A":
                if(score+11 > 21 ) {
                    value = 1;
                }else{
                    value = 11;
                }
                break;
            default:
                value = Integer.parseInt(card);
                break;
        }
        return value;
    }

    public static void deal(ArrayList<String[]> playerCards,int n){
        for(int i=0;i<n;i++) {
            createCard(playerCards);
        }
    }

    public static void checkForDuplicates(){
        duplicatedCardsInHand(myCards);
        duplicatedCardsPlayerAndBank(myCards,bankCards);
    }

    public static String getCard(int cardNumber,ArrayList<String[]> playerCards){
        return playerCards.get(cardNumber)[0];
    }

    public static void getCards(ArrayList<String[]> playerCards){
        for(int i=0;i<playerCards.size();i++) {
            System.out.println(playerCards.get(i)[0]+playerCards.get(i)[1]);
        }
    }

    public static void createCard(ArrayList<String[]> playerCard){
        String[] card = new String[2];
        card[0] = generateCardValue();
        card[1] = generateCardSign();
        playerCard.add(card);
        checkForDuplicates();
    }

    public static String generateCardSign(){
        Random r = new Random();
        String cardSigns = "HTCP";
        Character cardSign = cardSigns.charAt(r.nextInt(cardSigns.length()));

        return Character.toString(cardSign);
    }

    public static String generateCardValue(){
        Random rand = new Random();
        int randomNum = rand.nextInt((14 - 2) + 1) + 2;
        String cardValue = Integer.toString(randomNum);
        switch (randomNum){
            case 11:
                cardValue = "J";
                break;
            case 12:
                cardValue = "Q";
                break;
            case 13:
                cardValue = "K";
                break;
            case 14:
                cardValue = "A";
                break;
        }
        return cardValue;
    }

    public static void duplicatedCardsInHand(ArrayList<String[]> playerCards){
        for(int i=0;i<playerCards.size()-1;i++) {
            for(int j=i+1;j<playerCards.size();j++) {
                if((playerCards.get(i)[0].equals(playerCards.get(j)[0])) && (playerCards.get(i)[1].equals(playerCards.get(j)[1]))){
                    playerCards.remove(i);
                    createCard(playerCards);
                }
            }
        }
    }

    public static void duplicatedCardsPlayerAndBank(ArrayList<String[]> playerCards,ArrayList<String[]> bankCards){
        for(int i=0;i<playerCards.size();i++) {
            for(int j=0;j<bankCards.size();j++) {
                if((playerCards.get(i)[0].equals(bankCards.get(j)[0])) && (playerCards.get(i)[1].equals(bankCards.get(j)[1]))){
                    bankCards.remove(j);
                    createCard(bankCards);
                }
            }
        }
    }

}

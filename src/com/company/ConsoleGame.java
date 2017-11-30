package com.company;

import java.util.Scanner;

public class ConsoleGame implements Game {
    @Override
    public void run() throws Exception {

    }
//    public static BlackJackRules rules;
//
//    @Override
//    public void run() throws Exception {
//        Hand player = new Hand(250);
//        Hand dealer = new Hand();
//
//        rules = new BlackJackRules(player, dealer);
//
//        Scanner s = new Scanner(System.in);
//        int command = 0;
//        int betValue = 0;
//
//        while (command != 2) {
//            System.out.println("My credits : $" + player.getCredits());
//            System.out.println("1 - Place bets\n" +
//                    "2 - Quit");
//            command = s.nextInt();
//            switch (command) {
//                case 1:
//                    do {
//                        System.out.println("Place your bets:");
//                        betValue = s.nextInt();
//                    } while (betValue > player.getCredits() || betValue == 0);
//                    player.setCredits(rules.bet(betValue, player.getCredits()));
//                    System.out.println("Bet : $" + betValue + "\n");
//                    rules.deal();
//                    break;
//                case 2:
//                    System.exit(0);
//                default:
//                    System.out.println("Command not exist!");
//                    break;
//            }
//            int commandInner = 0;
//            while (commandInner != 2 && command == 1) {
//                if (player.getScore() < 21) {
//                    rules.playersCards();
//                    System.out.println("\n" + "1 - Hit\n" +
//                            "2 - Stand");
//                    commandInner = s.nextInt();
//
//                    switch (commandInner) {
//                        case 1:
//                            if (player.getScore() >= 21) {
//                                commandInner = 2;
//                                rules.stand(betValue);
//                            } else {
//                                rules.giveCard(player);
//                            }
//                            break;
//                        case 2:
//                            rules.stand(betValue);
//                            break;
//                        default:
//                            System.out.println("Command not exist!");
//                            break;
//                    }
//                } else {
//                    rules.stand(betValue);
//                    break;
//                }
//            }
//
//        }
//    }
}

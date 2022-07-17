package com.github.zipcodewilmington.casino.games.BoulderParchmentShears;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.ActiveAccount;
import com.github.zipcodewilmington.utils.TheScanner;

import java.util.Random;
import java.util.Scanner;

public class BPSEngine {
    Scanner scan = new Scanner(System.in);
    static Random randy = new Random();
    static Integer botRoll = 0;
    static String botThrow;

    public static void intro(int numPlayers){
        int choice;
        System.out.println("\nWelcome to Boulder Parchment Shears!\nThe game of mental dominance over your foes.\n");
        if (numPlayers == 1) {
            System.out.println("Since there is one player, you will be competing against our high-tech AI.");
            PvE();
        } else if (numPlayers == 2) {
            PvP();
        }
    }
    public static void BPSRules2P() {
        System.out.println("The rules are simple. Boulder beats Shears, Shears beats Parchment, Parchment beats Boulder.\n" +
                "Each player will make the same wager, winner gets all.\n\n");
    }

    //1-PLAYER GAME STUFF

    public static void PvE() {
        int wager;
        String p1;
        String p2;
        BPSRules2P();
        System.out.println("How much do you want to wager?\n");
        wager = Account.makeBet(ActiveAccount.activeAccounts.get(0));
        p1 = getThrow(1);
        p2 = AIThrow();
        if (pveWin(p1, p2, wager) == true)  {
            Account.deposit(ActiveAccount.activeAccounts.get(0), (wager*2));
        } else if (draw(p1, p2)) {
            Account.deposit(ActiveAccount.activeAccounts.get(0), (wager));
            PvE();
        } else System.out.println(ActiveAccount.activeAccounts.get(0).getAccountName() + " threw " + p1 + " and the AI threw " + p2 + "." +
                ActiveAccount.activeAccounts.get(0).getAccountName() + " lost :(\n\n");
        quitPvE();
    }
    public static void quitPvE(){
        int choice;
        choice = TheScanner.getNumber("1) Play Again\n2) Return to Main Menu\n");
        if (choice == 1) {PvE();}
        else Casino.splashScreen();
    }
    public static boolean pveWin(String p1, String p2, int wager){
        if (p1.equals(getWinner2P(p1, p2))) {
            System.out.println(ActiveAccount.activeAccounts.get(0).getAccountName() + "Wins! \n" +
                    ActiveAccount.activeAccounts.get(0).getAccountName() + " threw " + p1 + " and the AI threw " + p2 + ".\n" +
                    "You wagered " + wager + " and won " + (wager*2) + ". That amount has been deposited in your account.\n");
            return true;}
        else return false;
    }
    public static boolean draw(String p1, String p2) {
        if (p1.equals(p2)) {
            System.out.println("It's a draw! Wager's have been returned, play again.");
            return true;}
        else return false;
    }

    //2-PLAYER GAME STUFF
    public static void PvP() {
        int wager;
        String win;
        String p1;
        String p2;
        BPSRules2P();
        System.out.println(ActiveAccount.activeAccounts.get(0).getAccountName() + ", how much do the players want to wager?\n");
        wager = Account.makeBet(ActiveAccount.activeAccounts.get(0));
        Account.withdraw(ActiveAccount.activeAccounts.get(1), wager);
        p1 = getThrow(1);
        p2 = getThrow(1);
        win = getWinner2P(p1, p2);
        if (draw(p1, p2)) {
            PvP();
        } else pvpWin(p1, p2, wager);
        quitPvP();
    }
    public static void quitPvP(){
        int choice;
        choice = TheScanner.getNumber("1) Play Again\n2) Return to Main Menu\n");
        if (choice == 1) {PvP();}
        else Casino.splashScreen();
    }
    public static boolean pvpWin(String p1, String p2, int wager){
        if (p1.equals(getWinner2P(p1, p2))) {
            System.out.println(ActiveAccount.activeAccounts.get(0).getAccountName() + " threw " + p1 + " and " + ActiveAccount.activeAccounts.get(1).getAccountName() + " threw " + p2 +  ".\n\n" +
                    ActiveAccount.activeAccounts.get(0).getAccountName() + " WINS!!\n" +
                    "You wagered " + wager + " and won " + (wager*2) + ". That amount has been deposited in your account.\n");
            Account.deposit(ActiveAccount.activeAccounts.get(0), (wager*2));
            return true;
        } else {
            System.out.println(ActiveAccount.activeAccounts.get(1).getAccountName() + " threw " + p2 + " and " + ActiveAccount.activeAccounts.get(0).getAccountName() + " threw " + p1 +  ".\n\n" +
                    ActiveAccount.activeAccounts.get(1).getAccountName() + " WINS!!\n" +
                    "You wagered " + wager + " and won " + (wager*2) + ". That amount has been deposited in your account.\n");
            Account.deposit(ActiveAccount.activeAccounts.get(1), (wager*2));
            return false;}
    }





    public static String getThrow(int player){
        String pt;
        System.out.println("Player " + player + ", pick your sign.\n" +
                "1) Boulder\n" +
                "2) Parchment\n" +
                "3) Shears");
        while (true) {
            int choice = TheScanner.getNumber("");
            if (choice == 1) {
                pt = "boulder";
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            } else if (choice == 2) {
                pt = "parchment";
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            } else if (choice == 3) {
                pt = "shears";
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            } else System.out.println("That is not a valid choice, choose 1, 2, or 3.");
        }
        return pt;
    }
    public static String AIThrow(){
        botRoll = (randy.nextInt(3)+1);
        if (botRoll == 1) {botThrow = "boulder";}
        else if (botRoll == 2) {botThrow = "parchment";}
        else botThrow = "shears";
        return botThrow;
    }
    public static String getWinningMove(String handSign) {
        if (handSign.equals("boulder")) {
            return "parchment";
        } else if (handSign.equals("parchment")) {
            return "shears";
        } else return "boulder";
    }
    public static String getLosingMove(String handSign) {
        if (handSign.equals("boulder")) {
            return "shears";
        } else if (handSign.equals("parchment")) {
            return "boulder";
        } else return "parchment";
    }
    public static String getDraw2P(String handSign) {
        if (handSign.equals("boulder")) {
            return "shears";
        } else if (handSign.equals("parchment")) {
            return "boulder";
        } else return "parchment";
    }
    public static String getWinner2P(String player1, String player2) {
        String oneWinner = getLosingMove(player1);
        String oneLoser = getLosingMove(player1);
        if (player2.equals(oneWinner)) {return player1;}
        else if (player2.equals(oneLoser)) {return player2;}
        else {return "draw";}
    }

    public static void asciiArt(){
        System.out.println("""
                                
                    _______
                ---'   ____)
                      (_____)
                      (_____)
                      (____)
                ---.__(___)
                                
                    _______
                ---'   ____)____
                          ______)
                          _______)
                         _______)
                ---.__________)
                                
                    _______
                ---'   ____)____
                          ______)
                       __________)
                      (____)
                ---.__(___)
                
                
                """);
    }

}

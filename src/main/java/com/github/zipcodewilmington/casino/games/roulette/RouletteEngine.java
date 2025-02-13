package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.ActiveAccount;
import com.github.zipcodewilmington.utils.Ascii;
import com.github.zipcodewilmington.utils.TheScanner;

import java.util.Objects;
import java.util.Scanner;

public class RouletteEngine {
    Scanner scanner = new Scanner(System.in);
//    Account userAcct = new Account("O", "O", 2000);
    Account userAcct = ActiveAccount.activeAccounts.get(0);
    private Account acct;
    private Casino casino;
    int spinResult;
    int bet;
    int wager;
    boolean isGameRestart;
    int balance;

    //Welcomes player
    public String welcomeMessage() {
        return ("Welcome to the Roulette Table!");
    }

    public void startRouletteGame() {
        if (!isGameRestart) {
            Ascii.printAscii("ROULETTE", "\"", 300, 20, 24);
            System.out.println("\n");
            welcomeMessage();
            beginFromStart();
        }
        else {
            beginFromRestart();
        }
    }

    public void beginFromRestart() {
        beginFromStart();
    }

    //Allows player to place a different kind of bet
    public void beginFromStart() {
        acct = new Account();
        rouletteMenu();
        int userInput = TheScanner.getNumber("");

        //Menu option number 1 Straight-Up Bet
        if (userInput == 1) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            System.out.println("What's your bet? Pick a number, any number.");
            bet = TheScanner.getNumber("");
            placeStraightUpBet(wager, bet, balance);
        }
        //Menu option number 2 RED/EVEN Bet
        else if (userInput == 2) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            placeRedEvenBet(wager, bet, balance);
        }
        //Menu option number 3 BLACK/ODD Bet
        else if (userInput == 3) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            placeBlackOddBet(wager, bet, balance);
        }
        //Menu option number 4 First Dozen Bet
        else if (userInput == 4) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            placeFirstDozenBet(wager, bet, balance);
        }
        //Menu option number 5 Second Dozen Bet
        else if (userInput == 5) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            placeSecondDozenBet(wager, bet, balance);
        }
        //Menu option number 6 Third Dozen Bet
        else if (userInput == 6) {
            wagerPrompt();
            wager = TheScanner.getNumber("");
            acct.withdraw(userAcct, wager);
            placeThirdDozenBet(wager, bet, balance);
        }
    }

    /* The straight-up bet in roulette, is an inside bet on a single number
     (or group of single numbers). It pays out 35:1.
     */
    public int placeStraightUpBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if ((spinResult == bet)) {
            System.out.println("Your results are: " + spinResult + ". You win!");
            int winAmount = (wager * 35);
            acct.deposit(userAcct, winAmount);
            System.out.println("Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Please play again!");
        } else
            System.out.println("Sorry, better luck next time! Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Play again and try to get your money back!");
        System.out.println("\n");
        continueGamblingPrompt();
        return balance;
    }

    public int placeRedEvenBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if (spinResult % 2 == 0) {
            System.out.println("Well, aren't you a lucky one. You win!");
            int winAmount = (wager * 2);
            acct.deposit(userAcct, winAmount);
            System.out.println("Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Please play again!");
        } else
            System.out.println("Sorry, better luck next time! Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ".  Play again and try to get your money back!");
        continueGamblingPrompt();
        return balance;
    }

    public int placeBlackOddBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if (spinResult % 2 != 0) {
            System.out.println("Well, aren't you a lucky one. You win!");
            int winAmount = (wager * 2);
            acct.deposit(userAcct, winAmount);
            System.out.println("Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() +". Please play again!");
        } else
            System.out.println("Sorry, better luck next time! Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Please play again!");
        continueGamblingPrompt();
        return balance;
    }

    public int placeFirstDozenBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if ((spinResult >= 1) && (spinResult <= 12)) {
            System.out.println("Well, aren't you a lucky one. You win!");
            int winAmount = (wager * 2);
            acct.deposit(userAcct, winAmount);
            System.out.println("Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Please play again!");
        } else
            System.out.println("Sorry, better luck next time! Your new balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Play again and try to get your money back!");
        continueGamblingPrompt();
        return balance;
    }

    public int placeSecondDozenBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if ((spinResult >= 13) && (spinResult <= 24)) {
            System.out.println("Well, aren't you a lucky one. You win!");
            int winAmount = (wager * 2);
            acct.deposit(userAcct, winAmount);
            System.out.println("Your balance is " + ActiveAccount.activeAccounts.get(0).getBalance() +". Please play again!");
        } else
            System.out.println("Sorry, better luck next time! Your new balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Play again and try to get your money back!");
        continueGamblingPrompt();
        return balance;
    }

    public int placeThirdDozenBet(int wager, int bet, int balance) {
        acct = new Account();
        RouletteWheel wheel = new RouletteWheel();
        spinResult = wheel.spinWheel();
        if ((spinResult >= 25) && (spinResult <= 36)) {
            System.out.println("Well, aren't you a lucky one. You win!");
            int winAmount = (wager * 2);
            acct.deposit(userAcct, winAmount);
            System.out.println(" Your new balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ".");
        } else
            System.out.println("Sorry, better luck next time! Your new balance is " + ActiveAccount.activeAccounts.get(0).getBalance() + ". Play again and try to get your money back!");
        continueGamblingPrompt();
        return balance;
    }

    public void rouletteMenu() {
//        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, to Roulette! Please select a bet. Take a look at the Roulette Menu " +
                            "below and select a number to start having fun! \n" +
                "\n" +
                "1.) Place a Straight Up Bet. This covers the ball landing on numbers 00, 0, and 1-36 " +
                     "(payout is 35:1). \n" +
                "2.) Place a Red/Even Bet. This covers the ball landing on numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, \n" +
                "    22, 24, 26, 28, 30, 32, 34, 36 (payout is 1:1). \n" +
                "3.) Place a Black/Odd Bet. This covers the ball landing on numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, \n" +
                "    21, 23, 25, 27, 29, 31, 33, 35 (payout is 1:1). \n" +
                "4.) Place a 1st Dozen Bet. This covers the ball landing on numbers 1 - 12 (payout is 2:1). \n" +
                "5.) Place a 2nd Dozen Bet. This covers the ball landing on numbers 13 - 24 (payout is 2:1). \n" +
                "6.) Place a 3rd Dozen Bet. This covers the ball landing on numbers 25 - 36 (payout is 2:1). \n" +
                    "\n" +
                     "Select your bet from the menu. \n");
//        String userInput = scanner.nextLine();

    }

    public void wagerPrompt() {
        System.out.println("What's your wager for your bet?");

    }

    public void continueGamblingPrompt() {
        casino = new Casino();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type 'Y' for 'Yes' or 'N' for 'No'.");
        String userInput = scanner.nextLine();
        if (Objects.equals(userInput.toUpperCase(), "Y")) {
            isGameRestart = true;
            beginFromStart();
        } else if (Objects.equals(userInput.toUpperCase(), "N")) {
            System.out.println("Thank you for playing Roulette! We hope to see you soon!");
            casino.splashScreen();
        }
    }
}
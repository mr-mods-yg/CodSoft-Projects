package com.yashgarg.codsoft;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
    static int numberOfChances = 7;
    static int score = 0;
    public static void main(String[] args) {
        int round = 1;
        System.out.println("--------------------------------------");
        System.out.println("WELCOME TO GUESS THE NUMBER GAME..");
        System.out.println("RULES :");
        System.out.println("1. Number can be from 0 to 100 both inclusive");
        System.out.println("2. You have only 7 attempts to win the game");
        System.out.println("3. If you win the game you will be proceeded to next round");
        Scanner sc = new Scanner(System.in);
        Random rm = new Random();
        startGame(sc, rm, round);
    }
    private static void startGame(Scanner sc, Random rm, int round) {
        int correct_num = rm.nextInt(101); // range - 0 (inclusive) to 101 (exclusive)
        System.out.println("--------------------------------------");
        System.out.println("Starting Round " + round);
        boolean isGuessCorrect = false;
        int attemptsLeft = numberOfChances;
        while (!isGuessCorrect && attemptsLeft > 0) {
            System.out.print("Enter a number : ");
            int guessedNum = sc.nextInt();
            if (guessedNum == correct_num) {
                isGuessCorrect = true;
                System.out.println("Correct Number Found .. You Won !!!");
                score += attemptsLeft*2;
                // start the next round
                startGame(sc, rm, ++round);
                return;
            } else {
                attemptsLeft--;  // 1 attempt used if number is not correct
                System.out.print("Try Again ! Your guess is ");
                if (guessedNum > correct_num) {
                    System.out.println("too high");
                } else {
                    System.out.println("too low");
                }
            }
            System.out.printf("You now have %d attempts left\n", attemptsLeft);
        }
        System.out.println("--------------------------------------");
        System.out.print("Game Over !! ");
        System.out.println("Your Score is "+ score);
        System.out.println("--------------------------------------");

    }
}

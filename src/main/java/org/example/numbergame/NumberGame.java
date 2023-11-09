package org.example.numbergame;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public void start(){
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int generatedNumber = random.nextInt(100) +1;
        System.out.println("Welcome to the Game!");
        int userGuess;
        int numOfTries = 0;
        do {
            System.out.print("Enter your guess: ");
            userGuess = sc.nextInt();
            numOfTries++;
            if (numOfTries > 10){
                System.out.println("You've exceeded the limit of ties.");
                break;
            }
            if (userGuess == generatedNumber){
                System.out.println("Congratulations!! Your won...");
                System.out.println("Number of tries: " + numOfTries);
            }
            if (userGuess < generatedNumber){
                System.out.println("Your guess is low.");
            }
            if (userGuess > generatedNumber){
                System.out.println("Your guess is high.");
            }
        } while (generatedNumber != userGuess);

        System.out.println("Do you want to play again?");
        System.out.print("'Y' for yes 'N' for No: ");
        sc.nextLine();
        String userInput = sc.nextLine();
        if (userInput.equalsIgnoreCase("Y")) {
            start();
        }
        System.out.println("Have a good day!");
        sc.close();
    }
    public static void main(String[] args) {
        NumberGame game = new NumberGame();
        game.start();
    }
}

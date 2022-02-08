package com.example;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    public static void main(String[] args) {
        // purpose of program to let the user guess letters to a secret word, one a time
        // Users will have a limited number of guesses, program will keep track of letters that are wrong

        System.out.println("Welcome to the word guessing game! You'll guess one letter at a time.");
        boolean playGame = true;
        while (playGame) {
            playGame = runTheGame();
        }

    }

    private static boolean runTheGame() {
        //call the class to get the random word
        Random rand = new Random(); // create instance of the Random Class called rand

        Scanner scanner = new Scanner(System.in);
        //String[] wordList = new String[]{"fresh", "couch", "third", "yellow", "football", "proxy", "process", "penalties", "conversion"};
        String[] wordList = new String[] {"goodbye", "hello"};
        // create random index from the word List array
        int randomIndex = rand.nextInt(wordList.length);
        String myWord = wordList[randomIndex];


        //pass the string into the SecretWord constructor to create the objects for the game
        SecretWord secretWordObject = new SecretWord(myWord);
        int numOfLettersLeft = secretWordObject.getNumGuessesRemaining();
        int numOfGuessesLeft = secretWordObject.getNumGuessesRemaining();

        boolean playAgain = false;
        while (secretWordObject.getNumGuessesRemaining() > 0 && numOfLettersLeft > 0) {


            //todo turn this into a method
            System.out.println("************ Number of Wrong Guesses left: " + numOfGuessesLeft + "  ************");
            System.out.println();
            System.out.println("************** Letters Already Guessed ***********************");
            System.out.println(secretWordObject.getLettersGuessedString());
            System.out.println("*********************************************************");
            System.out.println();
            System.out.println(secretWordObject);

            //todo add in part to Try, Except - check against custom LetterValidationException
            System.out.println("What letter do you guess?");
            String strGuessedLetter = scanner.nextLine();

            // make a guess and return it here -->
            secretWordObject.makeGuess(strGuessedLetter);

            System.out.println(secretWordObject);

            //getting updated number of letters left in the word still to be guessed
            numOfLettersLeft = secretWordObject.getNumLettersRemaining();
            numOfGuessesLeft = secretWordObject.getNumGuessesRemaining();
            if (numOfLettersLeft == 0) {
                //String finalDisplay = secretWordObject.toString();
                System.out.println();
                System.out.println(secretWordObject);
                //todo turn this into a method for printing the banner
                System.out.println("**********************************");
                System.out.println("*****   CONGRATS, YOU WON!   *****");
                System.out.println("**********************************");
            } else if (numOfGuessesLeft == 0) {
                System.out.println("**********************************");
                System.out.println("*****   SORRY, YOU LOSE!!   ******");
                System.out.println("**********************************");
                System.out.println();
                System.out.println("The word was: " + myWord);

            }
        }
            System.out.println("Would you like to play again?");
            System.out.println("(y)es or (n)o");
            String inputPlayAgain = scanner.nextLine();

            if (inputPlayAgain.toLowerCase().equals("y")) {
                playAgain = true;
            }
        return playAgain;
    }
}

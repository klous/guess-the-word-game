package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GuessWord {
    public static void main(String[] args) {
        // purpose of program to let the user guess letters to a secret word, one a time
        // Users will have a limited number of guesses, program will keep track of letters that are wrong
        // load the words from the file into memory and store into a dictionary

        // call the new method to load external files categories into a map of word:category
        Map<String, String> myWordsAndCategories = loadWordsInMap();

        // put the keys (words) into this list-->


        // convert the list into an array, likely a way to directly convert the keys to an array
      //  String[] myWordsArray = myWordList.toArray(new String[0]);


        System.out.println("Welcome to the word guessing game! You'll guess one letter at a time.");
        boolean playGame = true;
        while (playGame) {
            playGame = runTheGame(myWordsAndCategories);
        }

    }

    private static Map<String,String> loadWordsInMap() {
        File wordsFile = new File("words_categories_list.csv");
        Map<String, String> wordCategory = new HashMap<>();
        try (Scanner fileReader = new Scanner(wordsFile)) {
            //loop through the file and add each word to the dictionary of words
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                //if NOT the header line for the CSV:
                if(!line.contains("word,category")){
                    //add word and category to our HashMap
                    String[] lineSplit = line.split(",");
                    String word = lineSplit[0];
                    String category = lineSplit[1];
                    wordCategory.put(word,category);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Did not find the file to load the words");
            System.out.println(e.getMessage());
        }
        return wordCategory;
    }

    private static String randomlyPickWord(String[] wordList){
        Random rand = new Random();
        int randomIndex = rand.nextInt(wordList.length);
        return wordList[randomIndex];
    }


    private static boolean runTheGame(Map<String, String> wordListAndCategories) {
        //call the class to get the random word
//        Random rand = new Random(); // create instance of the Random Class called rand

        Scanner scanner = new Scanner(System.in);
        //String[] wordList = new String[]{"fresh", "couch", "third", "yellow", "football", "proxy", "process", "penalties", "conversion"};
       // String[] wordList = new String[] {"goodbye", "hello"};
        // create random index from the word List array
//        int randomIndex = rand.nextInt(wordList.length);
//        String myWord = wordList[randomIndex];

        List<String> myWordList = new ArrayList<>(wordListAndCategories.keySet());

        String[] myWordsArray = myWordList.toArray(new String[0]);
        String myWord = randomlyPickWord(myWordsArray);
        String myCategory = wordListAndCategories.get(myWord);



        //pass the string into the SecretWord constructor to create the objects for the game
        SecretWord secretWordObject = new SecretWord(myWord, myCategory);
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

            System.out.println("Category: " + secretWordObject.getCategory());

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

            if (inputPlayAgain.equalsIgnoreCase("y")) {
                playAgain = true;
            }
        return playAgain;
    }
}

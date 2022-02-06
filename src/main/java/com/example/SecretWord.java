package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecretWord {

    // create the word
    private String word;
    public String getWord() {return word;}
    public void setWord(String word) {this.word = word;}

    private int numGuessesRemaining;
    public int getNumGuessesRemaining() {return numGuessesRemaining;}
    // holds the display of the secret word
   // private char[] displayArray;

    private final Character[] LETTERS_IN_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    // create a list of letters in the alphabet to keep track of letters NOT Guessed. As letters are guessed, remove them from this list.
    //todo update this as letters are guessed
    //todo check this list before taking away a guess or checking the word
    private List<Character> lettersNotGuessed = Arrays.asList(LETTERS_IN_ALPHABET);
    public Character[] getLettersNotGuessed() {
        // take the list the user letter display is being stored in and return an array
        Character[] lettersNotGuessedArray = new Character[lettersNotGuessed.size()];
        lettersNotGuessedArray = userCharDisplay.toArray(lettersNotGuessedArray);
        return lettersNotGuessedArray;
    }

    private List<Character> secretCharTracker = new ArrayList<>();


    private List<Character> userCharDisplay = new ArrayList<>();
    public Character[] getUserCharDisplay() {
        // take the list the user Char display is being stored in and return an array
        Character[] userCharArray = new Character[userCharDisplay.size()];
        userCharArray = userCharDisplay.toArray(userCharArray);
        return userCharArray;
    }
    // returns the display array
    //todo just make a toString @Override method ?

    // I think a character list can be converted to a String more directly
    public String getPrettyStringDisplay(){
        String returnString = "";
        for (int i = 0; i < userCharDisplay.size(); i++) {
            String letter = Character.toString(userCharDisplay.get(i));
            returnString += letter;

            //todo can clean this up by changing the stopping point above ^
//            if (i != charDisplayArray.size() - 1) { // if index is NOT at the last spot, add a space to the string being built
//                returnString +=" ";
//            }
        }
     return returnString;
    }

    // method to check
    private boolean isLetterInWord(String guessedLetter){
        //guess was in the remaining letters or not
        boolean guessinWord = false;
        for(Character c : secretCharTracker){
            if(guessedLetter.equalsIgnoreCase(c.toString())){
                guessinWord = true;
            }
        }
        // subtract 1 from number of guesses remaining when letter is NOT in word
        numGuessesRemaining --;
        return guessinWord;
    }

    //todo handle a DISPLAY that is shown to the user, including all the missing letters
    //make a guess and return a String of the updated word display
    public String makeGuess(String guessedLetter){
        String returnString = "";
        if(!isLetterInWord(guessedLetter)){
            return getPrettyStringDisplay();
        }else{ // letter is IN the thing, now need to check for duplicates and update the secret display and the one shown to user
            for (int i = 0; i< userCharDisplay.size(); i++){
                if(guessedLetter.equalsIgnoreCase(secretCharTracker.get(i).toString())){
                    userCharDisplay.set(i, guessedLetter.toLowerCase().charAt(0));
                    secretCharTracker.set(i, '*');
                    // also need to update our alphabet tracker
                }
            }
        }
        return getPrettyStringDisplay();
    }

    private void createSecretWordDisplay(){
        for(int i = 0 ; i< word.length();i++) {
            String s = word.substring(i, i+1);
            // create the display for the user, should be same length as input word
            userCharDisplay.add(i,'_');
            secretCharTracker.add(i, s.toLowerCase().charAt(0));
        }
    }
    // Create / construct a new com.example.SecretWord Object
    public SecretWord(String word){
        // make sure word being stored is lowercase
        this.word = word.toLowerCase();

        createSecretWordDisplay();

        // set the number of guesses at 7
        this.numGuessesRemaining = 7;

    }

}

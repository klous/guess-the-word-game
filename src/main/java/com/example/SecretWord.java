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

    private List<Character> lettersGuessed;

    public Character[] getLettersGuessed(){
        Character[] lettersGuessedArray = new Character[lettersGuessed.size()];
        lettersGuessedArray = lettersGuessed.toArray(lettersGuessedArray);
        return lettersGuessedArray;
    }

    private void addLetterGuessed(Character c){
        lettersGuessed.add(c);
    }


    public String getLettersGuessedString() {
        // take the list the user letter display is being stored in and return an array
        String returnString = "";
        for(Character c : lettersGuessed){
            returnString += c.toString();
        }
        return returnString;
    }


    private final Character[] LETTERS_IN_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    // create a list of letters in the alphabet to keep track of letters NOT Guessed. As letters are guessed, remove them from this list.
    private List<Character> lettersNotGuessed = Arrays.asList(LETTERS_IN_ALPHABET);

    /*private Character[] getLettersNotGuessed() {
        // take the list the user letter display is being stored in and return an array
        Character[] lettersNotGuessedArray = new Character[lettersNotGuessed.size()];
        lettersNotGuessedArray = userCharDisplay.toArray(lettersNotGuessedArray);
        return lettersNotGuessedArray;
    }

    public String getLettersNotGuessedString() {
        // take the list the user letter display is being stored in and return an array
        String returnString = "";
        Character[] lettersNotGuessedArray = new Character[lettersNotGuessed.size()];
        lettersNotGuessedArray = userCharDisplay.toArray(lettersNotGuessedArray);
        return returnString;
    }*/

    private List<Character> secretCharTracker = new ArrayList<>();

    //todo implement this method that checks if letter guessed is in the alphabet
    private Boolean isLetter(String letterGuessed){
        for (Character c : LETTERS_IN_ALPHABET){
            if(c.equals(letterGuessed.charAt(0))){
                return true;
            }
        }
        return false;
    }

    private List<Character> userCharDisplay = new ArrayList<>();
    //todo probably doesn't need to be public method
    public Character[] getUserCharDisplay() {
        // take the list the user Char display is being stored in and return an array
        Character[] userCharArray = new Character[userCharDisplay.size()];
        userCharArray = userCharDisplay.toArray(userCharArray);
        return userCharArray;
    }

    @Override
    public String toString(){
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
        boolean guessInWord = false;
        for(Character c : secretCharTracker){
            if(guessedLetter.equalsIgnoreCase(c.toString())){
                guessInWord = true;
                updateLettersNotGuessed(guessedLetter.charAt(0));
            }
        }
        // update the letters guessed display
        updateLettersNotGuessed(guessedLetter.charAt(0));
        return guessInWord;
    }

    private boolean isLetterGuessedWrongBefore(String guessedLetter){
        // check the list of letters that has been guessed that were WRONG
        boolean isLetterGuessedWrongBefore = true;
        for (Character c : lettersNotGuessed){
            if(guessedLetter.equalsIgnoreCase(c.toString())){
                isLetterGuessedWrongBefore = false;
            }
        }
        return isLetterGuessedWrongBefore;
    }

    private void updateLettersNotGuessed(Character c){
        List<Character> newLettersNotGuessed = new ArrayList<>();
        for(int i = 0; i<lettersNotGuessed.size(); i++){
            if (!lettersNotGuessed.get(i).equals(c)){
                newLettersNotGuessed.add(lettersNotGuessed.get(i));
            }
        }
        lettersNotGuessed = newLettersNotGuessed;
    }

    public int getNumLettersRemaining(){
        int numberOfLettersRemaining = 0;
        for(int i = 0; i< secretCharTracker.size(); i++){
            String s = String.valueOf(secretCharTracker.get(i));
            // if a STAR is found representing a not found letter, add to numberOfLettersRemainingCounter
            if (!s.equals("*")) {
                numberOfLettersRemaining ++;
            }
        }
        return numberOfLettersRemaining;
    }

    //make a guess and return a String of the updated word display
    public void makeGuess(String guessedLetter){

        //todo create helper function to make sure the input letter is a letter of alphabet

        //todo check if the letter was guessed previously
        if(!isLetterGuessedWrongBefore(guessedLetter) && !isLetterInWord(guessedLetter)){
            numGuessesRemaining --;
            //return toString();
        }else{ // letter is IN the thing, now need to check for duplicates and update the secret display and the one shown to user
            for (int i = 0; i< userCharDisplay.size(); i++){
                if(guessedLetter.equalsIgnoreCase(secretCharTracker.get(i).toString())){
                    userCharDisplay.set(i, guessedLetter.toLowerCase().charAt(0));
                    secretCharTracker.set(i, '*');
                    //remove the letters NOT guessed, and just keep a running total of the guessed letters to display
                    updateLettersNotGuessed(guessedLetter.charAt(0));
                    //todo make unit test for this
                    addLetterGuessed(guessedLetter.charAt(0));
                }
            }
        }
       // return toString();
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
        // initializes the char[] display to users
        createSecretWordDisplay();

        // set the number of guesses at 7
        this.numGuessesRemaining = 7;
        this.lettersGuessed = new ArrayList<>() ;

    }

}

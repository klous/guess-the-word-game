package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecretWord {

    // create the word
    private String word;
    private String category;
    public String getCategory() {return category;}

    private int numGuessesRemaining;
    public int getNumGuessesRemaining() {return numGuessesRemaining;}

    private List<Character> lettersGuessed;

    public Character[] getLettersGuessed(){
        Character[] lettersGuessedArray = new Character[lettersGuessed.size()];
        lettersGuessedArray = lettersGuessed.toArray(lettersGuessedArray);
        return lettersGuessedArray;
    }

    private void addLetterGuessed(Character c){
        if (!lettersGuessed.contains(c)){
            lettersGuessed.add(c);
        }
    }


    public String getLettersGuessedString() {
        // take the list the user letter display is being stored in and return an array
        String returnString = "";
        for(Character c : lettersGuessed){
            returnString += c.toString();
        }
        return returnString;
    }


    private final Character[] LETTERS_IN_ALPHABET_CHAR = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};



    // create a list of letters in the alphabet to keep track of letters NOT Guessed. As letters are guessed, remove them from this list.
    private List<Character> lettersNotGuessed = Arrays.asList(LETTERS_IN_ALPHABET_CHAR);

    private List<Character> secretCharTracker = new ArrayList<>();



    private Boolean isLetter(String letterGuessed){
        for (Character c : LETTERS_IN_ALPHABET_CHAR){
            String l = Character.toString(c);
            if(l.equalsIgnoreCase(letterGuessed)){
                return true;
            }
        }
        return false;
    }

    private List<Character> userCharDisplay = new ArrayList<>();


    @Override
    public String toString(){
        String returnString = "";
        for (int i = 0; i < userCharDisplay.size(); i++) {
            String letter = Character.toString(userCharDisplay.get(i));
            returnString += letter;
        }
     return returnString;
    }

    // method to check if letter is in Word
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
                break;
            }
        }
        return isLetterGuessedWrongBefore;
    }

    private void updateLettersNotGuessed(Character c){
        List<Character> newLettersNotGuessed = new ArrayList<>();
        for (Character character : lettersNotGuessed) {
            if (!character.equals(c)) {
                newLettersNotGuessed.add(character);
            }
        }
        lettersNotGuessed = newLettersNotGuessed;
    }

    public int getNumLettersRemaining(){
        int numberOfLettersRemaining = 0;
        for (Character character : secretCharTracker) {
            String s = String.valueOf(character);
            // if a STAR is found representing a not found letter, add to numberOfLettersRemainingCounter
            if (!s.equals("*")) {
                numberOfLettersRemaining++;
            }
        }
        return numberOfLettersRemaining;
    }


    //make a guess
    public String makeGuess(String guessedLetter){

        // call the helper methdo to check if the letter is an actual letter or not
        if(!isLetter(guessedLetter)){
            return "Your guess is not a letter of the alphabet";
        }

        if(!isLetterGuessedWrongBefore(guessedLetter) && !isLetterInWord(guessedLetter)){
            numGuessesRemaining --;
            addLetterGuessed(guessedLetter.charAt(0));
            return toString();
        }else{ // letter is IN the thing, now need to check for duplicates and update the secret display and the one shown to user
            for (int i = 0; i< userCharDisplay.size(); i++){
                if(guessedLetter.equalsIgnoreCase(secretCharTracker.get(i).toString())){
                    userCharDisplay.set(i, guessedLetter.toLowerCase().charAt(0));
                    secretCharTracker.set(i, '*');
                }
            }
        }
        return toString();
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
    public SecretWord(String word, String category){
        // make sure word being stored is lowercase
        this.word = word.toLowerCase();
        // initializes the char[] display to users
        createSecretWordDisplay();
        this.category = category;
        // set the number of guesses at 7
        this.numGuessesRemaining = 7;
        this.lettersGuessed = new ArrayList<>() ;

    }

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

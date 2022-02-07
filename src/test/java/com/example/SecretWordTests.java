package com.example;

import org.junit.Assert;
import org.junit.Test;

public class SecretWordTests {
    private SecretWord secretWord;


    @Test
    public void test_makeGuess_word_is_table_guessing_single_letter_with_single_occurrence(){
        // create / construct new secretWord object
        secretWord = new SecretWord("table");

        //guess a letter
        String actual = secretWord.makeGuess("b");
        String expectedString = "__b__";

        Assert.assertEquals("The secret word is \"table\", guess is \"b\", expecting return string: \"__b__\"", expectedString, actual);

    }

    @Test
    public void test_makeGuess_word_is_table_guessing_two_different_letters_bacK_to_back(){
        // create / construct new secretWord object
        secretWord = new SecretWord("table");

        //guess a letter
        String actual = secretWord.makeGuess("b");
        actual = secretWord.makeGuess("e");
        String expectedString = "__b_e";

        Assert.assertEquals("The secret word is \"table\", first guess is \"b\", then \"e\" expecting return string: \"__b_e\"", expectedString, actual);

    }

    @Test
    public void test_makeGuess_wrong_letter_guessed_not_in_word_should_reduce_number_of_guesses_remaining_by_1(){
        // create / construct new secretWord object
        secretWord = new SecretWord("table");
        int startingNumberOfGuesses = secretWord.getNumGuessesRemaining();
        //guess a letter
        secretWord.makeGuess("y");
        int updatedNumOfGuessesLeft = secretWord.getNumGuessesRemaining();

        String message = "The starting number of guesses was " + startingNumberOfGuesses + " then it should have gone down by 1";

        Assert.assertEquals(message, startingNumberOfGuesses-1, updatedNumOfGuessesLeft );

    }

    @Test
    public void test_makeGuess_same_wrong_letter_guessed_twice_in_arrow_should_should_only_reduce_number_of_guesses_by_1(){
        // create / construct new secretWord object
        secretWord = new SecretWord("table");
        int startingNumberOfGuesses = secretWord.getNumGuessesRemaining();
        //guess a letter
        secretWord.makeGuess("y");
        int updatedNumOfGuessesLeft = secretWord.getNumGuessesRemaining();
        secretWord.makeGuess("y");
        updatedNumOfGuessesLeft = secretWord.getNumGuessesRemaining();

        String message = "The starting number of guesses was " + startingNumberOfGuesses + " then it should have gone down by 1";

        Assert.assertEquals(message, startingNumberOfGuesses-1, updatedNumOfGuessesLeft );

    }

    @Test
    public void test_makeGuess_word_is_hello_guessing_single_l_with_two_ls_in_word(){
        // create / construct new secretWord object
        secretWord = new SecretWord("hello");
        //guess a letter
        String actual = secretWord.makeGuess("l");
        String expectedString = "__ll_";

        Assert.assertEquals("The secret word is \"hello\", guess is \"l\", expecting return string: \"__ll_\"", expectedString, actual);

    }

    @Test
    public void test_SecretWord_constructor_initial_secret_display_creates_right_number_of_underscores_for_num_letters(){
        String word = "characters";
        secretWord = new SecretWord((word));
        String myDisplay = secretWord.getPrettyStringDisplay();

        String expectingInitialSecretDisplay = "__________";
        Assert.assertEquals("word is: \"characters\", expecting it to create user word display: \"__________\"",expectingInitialSecretDisplay, myDisplay);

    }

}

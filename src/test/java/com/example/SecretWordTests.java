package com.example;

import org.junit.Assert;
import org.junit.Test;

public class SecretWordTests {
    private SecretWord secretWord;


    @Test
    public void test_makeGuess_word_is_table_guessing_single_letter_with_single_occurence(){
        // create / construct new secretWord object
        secretWord = new SecretWord("table");

        //guess a letter
        String actual = secretWord.makeGuess("b");

        String expectedString = "__b__";

        Assert.assertEquals("The secret word is \"table\", guess is \"b\", expecting return string: \"__b__\"", expectedString, actual);

    }

    @Test
    public void test_makeGuess_word_is_hello_guessing_single_letter_with_two_occurences(){
        // create / construct new secretWord object
        secretWord = new SecretWord("hello");

        //guess a letter
        String actual = secretWord.makeGuess("l");

        String expectedString = "__ll_";

        Assert.assertEquals("The secret word is \"hello\", guess is \"l\", expecting return string: \"__ll_\"", expectedString, actual);

    }

}

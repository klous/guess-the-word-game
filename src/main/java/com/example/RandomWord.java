package com.example;

import java.util.Random;
import java.util.Scanner;

public class RandomWord {
    private String actualSecretWord;

    public String getActualSecretWord() {
        return actualSecretWord;
    }

    public RandomWord(){
        //create a random word object from a list of words
        Random rand = new Random(); // create instance of the Random Class called rand
        Scanner scanner = new Scanner(System.in);
        String[] wordList = new String[] {"fresh", "couch", "third", "yellow", "football", "proxy", "process", "penalties", "conversion"};
        //String[] wordList = new String[] {"goodbye", "hello"};
        // create random index from the word List array
        int randomIndex = rand.nextInt(wordList.length);
        actualSecretWord = wordList[randomIndex];
    }
}

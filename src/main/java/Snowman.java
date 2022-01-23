import java.util.Random;
import java.util.Scanner;

public class Snowman {
    public static void main(String[] args) {
        // purpose of program to let the user guess letters to a secret word, one a time
        // Users will have a limited number of guesses, program will keep track of letters that are wrong

        Random rand = new Random(); // create instance of the Random Class called rand

        // initially use an array to store the words, separated by comma. like: word,another,third
        //todo: store the secret word list in a text file or CSV and read that file

        // keep prompting user for a letter as long as they still have one of their 9 guesses left OR word is not finished

        String[] wordList = new String[] {"hello", "goodbye", "third", "yellow", "football", "proxy", "process", "penalties", "conversion"};

        // create random index from the word List array
        int randomIndex = rand.nextInt(wordList.length);

        // test the randomness of my word selection
        for(int i =0; i<10; i++){
            int secondsToSleep = 1;
            randomIndex = rand.nextInt(wordList.length);
            System.out.println(wordList[randomIndex]);
        }
        


    }
}

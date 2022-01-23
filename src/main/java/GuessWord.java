import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    public static void main(String[] args) {
        // purpose of program to let the user guess letters to a secret word, one a time
        // Users will have a limited number of guesses, program will keep track of letters that are wrong

        Random rand = new Random(); // create instance of the Random Class called rand
        Scanner scanner = new Scanner(System.in);
        // initially use an array to store the words, separated by comma. like: word,another,third
        //todo: store the secret word list in a text file or CSV and read that file



        //String[] wordList = new String[] {"hello", "goodbye", "third", "yellow", "football", "proxy", "process", "penalties", "conversion"};
        String[] wordList = new String[] {"goodbye", "hello"};
        // create random index from the word List array
        int randomIndex = rand.nextInt(wordList.length);

        //get secret word from the array
        String secretWord = wordList[randomIndex];


        int numberOfGuessesLeft = 9;
        int totalLetters = secretWord.length();
        int remainingLetters = secretWord.length();

        // for testing, print the secret word
        System.out.println("Hint, for testing - the word is: '" + secretWord +"'");

        // print out the spaces for the letters

        // build an array of the characters to display on the screen
        char[] secretWordDisplay = new char[totalLetters];
        for (int i = 0 ; i <secretWord.length();i++) {
            secretWordDisplay[i] = '_';
            }



        String lettersGuessed = " ";
        // keep prompting user for a letter as long as they still have one of their 9 guesses left OR word is not finished
        //todo check for multiples of letters appearing inside the word

        //todo need to debug the part about multiple letters

        //todo refactor to make it so it goes through each index of the string and just looks at each letter

        while(numberOfGuessesLeft < 9 || remainingLetters > 0){
            System.out.println("********* Number of Wrong Guesses left: " + numberOfGuessesLeft + "  *********");
            System.out.println();
            System.out.println("********* Letters Guessed *********");
            System.out.println(lettersGuessed);
            System.out.println();
            // show the display of unknown letters and known letters
            String myDisplay = returnWordDisplay(secretWordDisplay);
            System.out.println(myDisplay);

            System.out.println("What letter do you guess?");
            String strGuessedLetter = scanner.nextLine();

            // get the first character in the string and put it in the char variable type
            char charGuessedLetter = strGuessedLetter.charAt(0);
            // check to see if the guessed letter is in the word at all, using indexOf
            int indexOfGuess = secretWord.indexOf(strGuessedLetter);
            // if NOT found, add letter to the display of letters guessed
            if (indexOfGuess < 0){
                lettersGuessed += strGuessedLetter;
                lettersGuessed += " ";
                numberOfGuessesLeft --;
            } else { // then if guessed letter is found, add that letter(s) to the array of secretWordDisplay (could be more than once)
                secretWordDisplay[indexOfGuess] = charGuessedLetter;
                // then search the remaining substring from there until end of the string

                String restOfString = secretWord.substring(indexOfGuess+1); // put the remaining substring in this string
                for(int i = 0; i < restOfString.length(); i++) {
                    int indexOfGuessRestOfString = restOfString.indexOf(strGuessedLetter);
                    if (indexOfGuessRestOfString >= 0) {
                        // determine the index of the original secret word
                        secretWordDisplay[indexOfGuess+indexOfGuessRestOfString] = charGuessedLetter;
                    } else {
                        break;
                    }
                }

            }
            if (numberOfGuessesLeft == 0) {
                System.out.println("***   SORRY YOU LOSE!!   ****");
                break;
            }
            //
        }


    }
    // display the letters guessed in a format, putting a space between each
    public static String returnWordDisplay(char[] charArray) {
        String returnString = "";
        for (int i = 0 ;i <charArray.length;i++) {
            String letter = String.valueOf(charArray[i]);
            returnString += letter;
            // System.out.print(secretWordDisplay[i]);
            if (i != charArray.length - 1) { // if index is NOT at the last spot, print out a space
                returnString +=" ";
                // System.out.print(" ");
            }
        }
        return returnString;
    }

}

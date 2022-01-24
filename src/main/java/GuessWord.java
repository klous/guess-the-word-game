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


        int numberOfGuessesLeft = 5;
        int totalLetters = secretWord.length();
        int numberOfRemainingLetters = secretWord.length();

        // for testing, print the secret word
        System.out.println("Hint, for testing - the word is: '" + secretWord +"'");

        // print out the spaces for the letters

        // build an array of the characters to display on the screen

        // this creates the initial display of letters in a char array
        char[] secretWordDisplay = new char[totalLetters];
        for (int i = 0 ; i <secretWord.length();i++) {
            secretWordDisplay[i] = '_';
            }

        //todo check if a single letter was already guessed. and ideally, as error correction if that letter is part of alphabet.
        final char[] LETTERS_IN_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        String lettersGuessed = " ";
        // keep prompting user for a letter as long as they still have one of their 9 guesses left and there are remaining letters to guess

        while(numberOfGuessesLeft > 0 && numberOfRemainingLetters > 0){
            System.out.println("********* Number of Wrong Guesses left: " + numberOfGuessesLeft + "  *********");
            System.out.println();
            System.out.println("********* Letters Guessed *********");
            System.out.println(lettersGuessed);
            System.out.println();
            // show the display of unknown letters and known letters
            String myDisplay = returnWordDisplay(secretWordDisplay);

            // Show current display / status in game
            //format it so it looks better, more readable
            System.out.println(myDisplay);

            System.out.println("What letter do you guess?");
            String strGuessedLetter = scanner.nextLine();


            // call this method to create a char array of the letters of the secret word
           // char[] lettersInCharArray = buildCharArrayOfWord(secretWord);


            // need to check if letter in there at all, so use indexOf
            int indexOfGuess = secretWord.indexOf(strGuessedLetter);
            // if NOT found, add letter to the display of letters guessed and reduce the number of guesses left by 1
            if (indexOfGuess < 0){
                lettersGuessed += strGuessedLetter;
                lettersGuessed += " ";
                numberOfGuessesLeft --;
            } else { // letter is found somewhere, call this method to find all the instances of it
                secretWordDisplay = updateWordDisplayGuess(strGuessedLetter, secretWord, secretWordDisplay);

            }
            numberOfRemainingLetters = checkNumberOfRemainingLetters(secretWordDisplay);
            //
        }
        // while game loop ended
        if (numberOfRemainingLetters == 0) {
            System.out.println("**********************************");
            System.out.println("*****   CONGRATS, YOU WON!   *****");
            System.out.println("**********************************");
        } else if(numberOfGuessesLeft == 0) {
            System.out.println("**********************************");
            System.out.println("*****   SORRY, YOU LOSE!!   ******");
            System.out.println("**********************************");

        }

    }
    // build an array of the letters of the secret word
    public static char[] buildCharArrayOfWord(String secretWord) {
        // create array length of the word
        char arrayCharSpaces[] = new char[secretWord.length()];
        for(int i = 0 ; i< secretWord.length();i++) {
            String s = secretWord.substring(i, i+1);
            //get the letter at index 0 and put in char value
            arrayCharSpaces[i] = s.charAt(0);
        }

        return arrayCharSpaces;
    }


   //  display the letters guessed in a format, putting a space between each (making it look nicer)
    public static String returnWordDisplay(char[] displayArray) {
        String returnString = "";
        for (int i = 0 ;i <displayArray.length;i++) {
            String letter = Character.toString(displayArray[i]);
            returnString += letter;
            // System.out.print(secretWordDisplay[i]);
            if (i != displayArray.length - 1) { // if index is NOT at the last spot, add a space to the string being built
                returnString +=" ";
                // System.out.print(" ");
            }
        }
        return returnString;
    }
// pass in letter guessed, the secret word, and the current game board and it will find all the matches
    public static char[] updateWordDisplayGuess(String strLetterGuessed, String secretWord, char[] currentWordDisplay) {
        // create new String array to hold the blanks and successful guesses of the word
        char[] outputTrackingGameBoard = new char[secretWord.length()];
        // iterate through the passed in array and add it to the output Array (basically make a copy of the contents)
        for (int i =0;i< currentWordDisplay.length; i++) {
            outputTrackingGameBoard[i] = currentWordDisplay[i];
        }
        // I think this loop would be better just checking each string...for .equals
        for (int i=0; i< outputTrackingGameBoard.length; i++) {
            // check each letter, get the slice using substring of each letter
            String substringSliceLetter = secretWord.substring(i, i+1);
            // if letter matches the string the for loop is looking at that index
            if(substringSliceLetter.equalsIgnoreCase(strLetterGuessed)) {
                outputTrackingGameBoard[i] = strLetterGuessed.charAt(0);
            }
        }
        return outputTrackingGameBoard;
    }

    public static int checkNumberOfRemainingLetters (char[] currentWordDisplay) {
        int numberOfLettersRemaining = 0;
        for(int i = 0; i< currentWordDisplay.length; i++){
            String s = String.valueOf(currentWordDisplay[i]);
            // if a an underscore is found, representing a not found letter, add to numberOfLettersRemainingCounter
            if (s.equals("_")) {
                numberOfLettersRemaining ++;
            }
        }
        return numberOfLettersRemaining;
    }

}

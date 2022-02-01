import java.util.ArrayList;
import java.util.List;

public class SecretWord {

    // create the word
    private String word;
    public String getWord() {return word;}
    public void setWord(String word) {this.word = word;}

    // holds the display of the secret word
   // private char[] displayArray;

    private List<Character> charDisplayArray = new ArrayList<>();


    // returns the display array
    //todo just make a toString @Override method ?

    public String getPrettyStringDisplay(){
        String returnString = "";
        for (int i = 0 ;i <charDisplayArray.size();i++) {
            String letter = Character.toString(charDisplayArray.get(i));
            returnString += letter;

            //todo can clean this up by changing the stopping point above ^
            if (i != charDisplayArray.size() - 1) { // if index is NOT at the last spot, add a space to the string being built
                returnString +=" ";
            }
        }
     return returnString;
    }

    private boolean makeAGuess(String guessedLetter){
        //guess was successful or not
        boolean guessWorked = false;

        // iterate through the current character array
        return guessWorked;
    }

    // Create / construct a new SecretWord Object
    public SecretWord(String word){
        this.word = word;
        //charDisplayArray = new ArrayList<>();
        for(int i = 0 ; i< word.length();i++) {
            String s = word.substring(i, i+1);
            //get the letter at index 0 and put in char value
            charDisplayArray.add(i,s.charAt(0));
        }

    }

}

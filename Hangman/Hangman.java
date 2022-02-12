import java.util.Random;
import java.util.Scanner;

public class Hangman {
    
    private static final int TOTAL_ATTEMPTS = 6;

    // method that randomly picks a word from a word bank
    public static char[] pickWord(){
        String[] words = {"apple", "banana", "car", "donkey", "map", "computer", "university"};
        Random generator = new Random();
        String chosen_word = words[generator.nextInt(words.length)];
        char[] wordArray = chosen_word.toCharArray();
        return wordArray;
    }
    
    public static void printHangman(int triesRemaining){
        String[] hangmanPictures = {"  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========", 
        "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========", 
        "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="};

    System.out.println(hangmanPictures[hangmanPictures.length - triesRemaining - 1]);

    }
    
    public static void printWord(char[] word){
        for (int i=0; i<word.length; i++){
            System.out.print(word[i] + " ");
        }
        System.out.print("\n");
    }
    
    // method that returns true if the game has been won (all letters guessed)
        public static boolean ifOver(char[] guessingArray){
        for (int i=0; i<guessingArray.length; i++){
            if (guessingArray[i] == '_'){
                return false;
            }
        }
        return true;
    }
    
    /* method that returns true if letter is in the word, and rewrites empty
    spaces with correctly guessed letters */
    public static boolean inWord(char[] guessingArray, char[] word, char letter){
        boolean contains = false; 
        for (int i=0; i<word.length; i++){
            if (letter == word[i]){
                guessingArray[i] = letter;
                contains = true;
            }
        }
        return contains;
    }
    
    public static int guessLetter(char[] guessingArray, char[] word, int triesRemaining){
        
        Scanner scanLetter = new Scanner(System.in); 
        System.out.println("Guess a Letter.");
        char letter = scanLetter.next().charAt(0);
        String wordString = new String(word);

        if (inWord(guessingArray, guessingArray, letter) == true){
            System.out.println("You have already tried that letter. Try again.");
            guessLetter(guessingArray, word, triesRemaining);
        }
        
        if (inWord(guessingArray, word, letter) == true){
            System.out.println("\nCorrect. You have " + triesRemaining + " tries remaining.");
            printHangman(triesRemaining);
            printWord(guessingArray);
            if (ifOver(guessingArray) == false){
                guessLetter(guessingArray, word, triesRemaining);
            }
            else {
                System.out.println("Game over! The word was '" + wordString + "'. Good job!");
            }
        }
        
        else {
            triesRemaining -= 1;
            printHangman(triesRemaining);
            System.out.println("'" + letter + "' is not in the word. You have " + triesRemaining + " tries remaining.");
            if (triesRemaining == 0){
                System.out.println("Game over! The word was '" + wordString + "'.");
                return 1;
            } 
            else {
            guessLetter(guessingArray, word, triesRemaining);
            }
        }

        return 0;
    }
    
    public static void playGame(){
        
        char[] word = pickWord();
        char[] guessingArray = new char[word.length]; 
        for (int i=0; i<guessingArray.length; i++){
            guessingArray[i] = '_';
        }
        
        printWord(guessingArray);
        guessLetter(guessingArray, word, TOTAL_ATTEMPTS);
        
    }
    
	public static void main(String[] args) {
	    System.out.println("Welcome to Hangman!");
	    playGame();
	}
}

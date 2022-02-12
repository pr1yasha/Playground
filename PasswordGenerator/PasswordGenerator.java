import java.util.Scanner; 
import java.util.Random;

public class PasswordGenerator{
    
    private static final int ALPHABET_LENGTH = 26;
    private static final int NUMBER_LENGTH = 10;
    private static final int SYMBOL_LENGTH = 20;

    public static void passwordStrengthen(char password[], char allCharacters[], int length, int startIndex){
        Random generator = new Random();
        int randomInt = generator.nextInt(password.length);

        if (password[randomInt] == '-'){
            password[randomInt] = allCharacters[generator.nextInt(length) + startIndex];
        }
        
        else {
            passwordStrengthen(password, allCharacters, length, startIndex);
        }
    }

    public static void passwordGen(int length){
        char[] allCharacters = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '?', '~', '_', '{', '}', '-', '+', '=', ':', ';','0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char password[] = new char[length];
        for (int i=0; i<length; i++){
            password[i] = '-';
        }
        
        Random generator = new Random();
    
        passwordStrengthen(password, allCharacters, SYMBOL_LENGTH, 0); // adds symbol
        passwordStrengthen(password, allCharacters, NUMBER_LENGTH, SYMBOL_LENGTH); // adds number
        passwordStrengthen(password, allCharacters, ALPHABET_LENGTH, SYMBOL_LENGTH+NUMBER_LENGTH); // adds uppercase letter
        passwordStrengthen(password, allCharacters, ALPHABET_LENGTH, SYMBOL_LENGTH+NUMBER_LENGTH+ALPHABET_LENGTH); // adds lowercase letter

        // fills in rest of password with random characters
        for (int i=0; i<length; i++){
            if (password[i] == '-'){
                password[i] = allCharacters[generator.nextInt(allCharacters.length)];
            }
        }
        
        for (int i=0; i<length; i++){
            System.out.print(password[i]);
        }
        System.out.print("\n");
    }
    
    public static int userInput(){
        
        Scanner scanPasswordAmt = new Scanner(System.in); 
        System.out.println("How many passwords would you like to create?");
        int numOfPasswords = scanPasswordAmt.nextInt();
        
        Scanner scanPasswordLen = new Scanner(System.in); 
        System.out.println("How long would you like your password to be? (minimum of 8 characters)");
        int passwordLength = scanPasswordLen.nextInt();
        
        if (passwordLength < 8){
            System.out.println("Too short. Strong passwords should have a minimum of 8 characters!");
            return(1);
        }
        
        for (int i=0; i<numOfPasswords; i++){
            passwordGen(passwordLength);
        }
        
        return 0;
    }

    public static void main(String []args){
        userInput();
     }
}

/*****************************
Created by Priyasha Ghosh 12/02/2022
Creates nonsensical haikus :)
*****************************/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import java.util.Random;
import java.util.stream.Stream;


public class HaikuGenerator {

    public static final int NUMBER_OF_LINES = 172823;

    public static void main (String[] args) throws IOException {
        wordFinder(5);
        System.out.print("\n");
        wordFinder(7);
        System.out.print("\n");
        wordFinder(5);
    }

    // method that calculates syllables
    public static int calculateSyllables(String word){
        char[] wordArray = word.toCharArray();
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        int syllables = 0;
        int vowelCount = 0;

        for (int i=0; i < wordArray.length; i++){
            if (vowels.contains(wordArray[i])){
                vowelCount ++;
                // registers 'er' suffix as a new syllable
                if (wordArray[i] == 'e' && (i==wordArray.length - 2) && (wordArray[i + 1] == 'r')){
                    syllables++;
                }

                // ensures diphthong/triphthongs aren't counted as new syllables
                else if (i>0 && vowels.contains((wordArray[i-1]))){
                }
                else if (wordArray[i] == 'e' && vowelCount > 1 && (i==wordArray.length - 1)){
                }

                else {
                    syllables++;
                }
            }

            // counts syllables with 'y' acting as a vowel
            if (wordArray[i] == 'y' && (!vowels.contains(wordArray[i]-1)) && (i==wordArray.length - 1)){
                syllables++;
            }
        }
        return syllables;
    }

    // method that prints a line of a certain number of syllables
    public static int wordFinder(int syllableNum) throws IOException {
        Random generator = new Random();
      
        // gets random word from file and uses recursion to continue until number of syllables for that line has been fulfilled
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\mghosh22\\Desktop\\DailyProgrammer\\enable1.txt"))) {
                int x = generator.nextInt(NUMBER_OF_LINES);
                String currWord = lines.skip(x-1).findFirst().get();
                int syllables = calculateSyllables(currWord);
                if (syllables <= syllableNum) {
                    System.out.print(currWord + " ");
                    if (syllableNum-syllables > 0){
                        wordFinder(syllableNum-syllables);
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    wordFinder(syllableNum);
                }

            return 0;
        }
    }

}


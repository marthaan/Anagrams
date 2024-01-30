// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Test AnagramDictionary class using user input.
 * Contains a helper test method.
 */
public class AnagramDictionaryTester {

   /**
    * Asks user to input fileName that is used to
    * create our AnagramDictionary. Error checks if input
    * file doesn't exist or has duplicates. If no errors, creates
    * and prints our dictionary and also tests getAnagramsOf().
    * @param args
    * @throws FileNotFoundException if file does not exist
    * @throws IllegalDictionaryException if file contains any duplicate words
    */
   public static void main(String[] args) throws FileNotFoundException, IllegalDictionaryException {
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter file name: ");
      String fileName = in.next();

      AnagramDictionary dictionary = new AnagramDictionary(fileName);

      System.out.println(dictionary);

      getAnagramsOfTester(dictionary, "gdo");
      getAnagramsOfTester(dictionary, "god");
      getAnagramsOfTester(dictionary, "em");
      getAnagramsOfTester(dictionary, "EFILR");
      getAnagramsOfTester(dictionary, "");
      getAnagramsOfTester(dictionary, "martha");
   }

   /**
    * Helper function to test getAnagramsOf() for a
    * potential key in an anagram dictionary.
    * @param dict the desired anagram dictionary to use to find the anagrams
    * @param string a String that could potentially be a key in our anagram dictionary
    */
   private static void getAnagramsOfTester(AnagramDictionary dict, String string) {
      System.out.println("Get anagrams of '" + string + "': " + dict.getAnagramsOf(string));
      // No expected print statement since it will depend on the given fileName
   }
}

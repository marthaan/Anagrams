// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Test Rack class using user input
 */
public class RackTester {

   /**
    * Asks user to input a String of letters which is used to create a Rack.
    * Prints the Rack to test constructor.
    * Tests getSubsets() using the Rack and a chosen anagram dictionary.
    * Error checks if anagram dictionary file doesn't exist or has duplicates.
    * @param args
    * @throws FileNotFoundException if anagram dictionary file is not found
    * @throws IllegalDictionaryException if anagram dictionary file contains any duplicates
    */
   public static void main(String[] args) throws FileNotFoundException, IllegalDictionaryException{
      Scanner in = new Scanner(System.in);

      System.out.println("Rack?");
      String letters = in.next();

      Rack rack = new Rack(letters);
      System.out.println("Rack: " + rack);

      ArrayList<String> subsets = rack.getSubsets(rack, new AnagramDictionary("testFiles/tinyDictionary.txt"));
      System.out.println("Valid subsets: " + subsets);
   }
}

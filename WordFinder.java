// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Contains main method for our program and necessary helper functions
 */
public class WordFinder {

   /**
    * Gets a fileName based on user input, which is used to create the
    * anagram dictionary desired. When the dictionary is created, it catches the two errors
    * that could be thrown. If no errors, a Rack is created from the user-provided String,
    * which is then used to find all valid subsets of the Rack and their scores.
    * Displays necessary info regarding a Rack to be used in Scrabble.
    * @param args
    */
   public static void main(String[] args) {
      String fileName;

      if (args.length == 0) {
         fileName = "sowpods.txt";
      }
      else {
         fileName = args[0];
      }

      boolean done = false;
      while (!done) {
         try {
            AnagramDictionary dictionary = new AnagramDictionary(fileName);

            Scanner in = new Scanner(System.in);
            String rackInput = "";

            System.out.println("Type . to quit.");

            while (!rackInput.equals(".")) {
               System.out.print("Rack? ");
               rackInput = in.next();

               Rack rack = new Rack(rackInput);

               ArrayList<String> subsets = rack.getSubsets(rack, dictionary);

               int numWords = subsets.size();

               if (!rackInput.equals(".")) {
                  System.out.println("We can make " + numWords + " words from " + '"' + rackInput + '"');
               }

               if (numWords != 0) {
                  System.out.println("All of the words with their scores (sorted by score):");
               }

               if (!subsets.isEmpty()) {
                  printSorted(subsets, System.out);
               }
            }

            in.close();
         }
         catch (IllegalDictionaryException exception) {
            System.out.println(exception.getMessage());
            done = true;
         }
         catch (FileNotFoundException exception) {
            System.out.println("ERROR: Dictionary file " + fileName + " does not exist.");
            System.out.println("Exiting program.");
         }
         done = true;
      }
   }

   /**
    * Print all subsets sorted by their scores (decreasing),
    * and if they have the same scores sort them alphabetically (increasing).
    */
   private static void printSorted(ArrayList<String> subsets, PrintStream out) {
      Map<String, Integer> sortedScores = new TreeMap<>();

      for (String subset : subsets) {
         ScoreTable scoreTable = new ScoreTable();
         int wordScore = scoreTable.getWordScore(subset);
         sortedScores.put(subset, wordScore);
      }

      ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(sortedScores.entrySet());
      Collections.sort(list, new entryComparator());

      for(Map.Entry<String, Integer> entry : list) {
         out.println(entry.getValue() + ": " + entry.getKey());
      }
   }

   /**
    * Comparator class definition used to sort the desired subsets.
    */
   static class entryComparator implements Comparator<Map.Entry<String, Integer>> {

      /**
       * Compares two map entries for order, sorting decreasingly by integer.
       * @param o1 the first object to be compared.
       * @param o2 the second object to be compared.
       * @return a result that is negative, 0, or positive, indicating if the 1st object comes
       * before, equally, or after in order
       */
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
         return o2.getValue() - o1.getValue();
      }
   }
}
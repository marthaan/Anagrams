// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
   A Rack of Scrabble tiles
 */
public class Rack {

   /**
    * Representation invariant:
    *
    * This Rack contains an ArrayList of strings which represent valid characters
    * that are a part of the Rack. This list must be sorted alphabetically.
    */
   private ArrayList<String> rack;

   /**
    * Creates a Rack object given a String of letters.
    * @param letters potentially valid letters to be added to the Rack
    */
   public Rack(String letters) {
      rack = new ArrayList<>();

      String sortedLetters = sortString(letters);

      for (Character letter : sortedLetters.toCharArray()) {
         if (!Character.isWhitespace(letter) && Character.isLetter(letter)) {
            rack.add(letter + "");
         }
      }
   }

   /**
    * Returns an ArrayList of strings, with each String being a subset of the
    * given Rack that is in the given anagram dictionary.
    * @param givenRack the Rack letters we are using to form subsets from
    * @param dict the anagram dictionary we will use to see if a subset is an anagram
    * @return an ArrayList<String> of all valid subsets
    */
   public ArrayList<String> getSubsets(Rack givenRack, AnagramDictionary dict) {
      ArrayList<String> allSubsets = allSubsetsWrapper(givenRack);

      ArrayList<String> dictSubsets = new ArrayList<>();

      for (String subset : allSubsets) {
         if (!subset.isEmpty()) {
            ArrayList<String> subsetAnagrams = dict.getAnagramsOf(subset);

            for (String anagram : subsetAnagrams) {
               dictSubsets.add(anagram);
            }
         }
      }

      return dictSubsets;
   }

   /**
    * Creates a String representation of a Rack.
    * @return String representation
    */
   public String toString() {
      String rackString = "";

      for (String letter : rack) {
         rackString += letter;
      }

      return rackString;
   }

   /**
    * Finds all subsets of the multiset starting at position k of the unique String and mult array.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length(), 0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    * each subset is represented as a String that can have repeated characters in it.
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
    * Wrapper method for allSubsets.
    * Uses a given Rack to create a String of only the unique
    * letters from the Rack and create an integer array of
    * multiplicities which correspond to each unique letter in unique.
    * Then calls allSubsets with the correct starting parameters.
    * @param rack the Rack to obtain subsets from
    * @return the result of allSubsets, which is an ArrayList<String> of
    * all subsets of the Rack (not necessarily in the anagram dictionary)
    */
   private static ArrayList<String> allSubsetsWrapper(Rack rack) {
      String rackStr = rack.toString();

      String unique = "";

      Map<String, Integer> freqMap = new HashMap<>();

      for (Character character : rackStr.toCharArray()) {
         String letter = character + "";
         Integer freq = 1;

         if (freqMap.containsKey(letter)) {
            freq = freqMap.get(letter) + 1;
            freqMap.put(letter, freq);
         }
         else {
            freqMap.put(letter, freq);
            unique += letter;
         }
      }

      int[] mult = new int[unique.length()];

      for (int i = 0; i < unique.length(); i++) {
         String letter = unique.charAt(i) + "";
         Integer freq = freqMap.get(letter);

         mult[i] = freq;
      }

      return allSubsets(unique, mult, 0);
   }

   /**
    * Sorts a String alphabetically by its characters.
    * @param string the desired String to be sorted
    * @return string sorted alphabetically
    */
   private String sortString(String string) {
      char[] charArray = string.toCharArray();
      Arrays.sort(charArray);
      String sortedString = String.valueOf(charArray);

      return sortedString;
   }
}

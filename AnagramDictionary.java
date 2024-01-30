// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    * Representation invariant:
    *
    * This anagram dictionary contains a HashMap, whose keys are all the
    * unique, alphabetically sorted words from the given file and whose
    * values are sets of anagrams of each key. Each anagram in each set
    * must be also a word from the given file.
    */
   private Map<String, Set<String>> anagramDict;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    * @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {
      anagramDict = new HashMap<>();

      File file = new File(fileName);
      Scanner fileScanner = new Scanner(file);

      while (fileScanner.hasNextLine()) {
         String originalWord = fileScanner.nextLine();

         if (!originalWord.isEmpty()) {
            String key = sortString(originalWord);

            Set<String> values = anagramDict.getOrDefault(key, new HashSet<>());

            if (values != null && !values.add(originalWord)) {
               throw new IllegalDictionaryException("ERROR: Illegal dictionary: " +
                     "dictionary file has a duplicate word: " + originalWord + "\nExiting program.");
            }
            else {
               values.add(originalWord);
               anagramDict.put(key, values);
            }
         }
      }

      fileScanner.close();
   }

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param string string to process
    * @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      ArrayList<String> anagrams = new ArrayList<>();

      String sortedWord = sortString(string);

      if (anagramDict.containsKey(sortedWord)) {
         Set<String> values = anagramDict.get(sortedWord);
         for (String value : values) {
            anagrams.add(value);
         }
      }

      return anagrams;
   }

   /**
    * Returns a String representation of this anagram dictionary.
    * @return String representation
    */
   public String toString() {
      Set<Map.Entry<String, Set<String>>> entrySet = anagramDict.entrySet();
      String entrySetString = entrySet.toString();

      return entrySetString;
   }

   /**
    * Sorts the given String alphabetically and
    * returns the sorted String.
    * @param string
    * @return the alphabetically sorted String
    */
   private String sortString(String string) {
      char[] charArray = string.toCharArray();
      Arrays.sort(charArray);
      String sortedString = String.valueOf(charArray);

      return sortedString;
   }
}
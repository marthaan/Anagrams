// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

/**
 * Test ScoreTable class using hard-coded input.
 */
public class ScoreTableTester {

   /**
    * Tests ScoreTable constructor, getLetterScore(), and
    * getWordScore() by using alphabet strings to print out each
    * letter and its associated score, stored in ScoreTable.
    * Contains helper method to simplify output statements.
    * @param args
    */
   public static void main(String[] args) {
      ScoreTable scores = new ScoreTable();

      String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
      String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

      System.out.println("-----TEST LOWER CASE LETTERS-----");
      for (char lowerLetter : lowerAlphabet.toCharArray()) {
         System.out.println("Letter: " + lowerLetter);
         System.out.println("Score: " + scores.getLetterScore(lowerLetter));
         printExpected(lowerLetter);

      }
      System.out.println();

      System.out.println("-----TEST UPPER CASE LETTERS-----");
      for (char upperLetter : upperAlphabet.toCharArray()) {
         System.out.println("Letter: " + upperLetter);
         System.out.println("Score: " + scores.getLetterScore(upperLetter));
         printExpected(Character.toLowerCase(upperLetter));
      }

      System.out.println("-----TEST WORDS-----");
      System.out.println("Word score of 'martha' = " + scores.getWordScore("martha"));
      System.out.println("Expected: 11");
   }

   /**
    * Helper method to test getLetterScore() of both lower and upper
    * case letters similarly.
    * @param letter
    */
   private static void printExpected(char letter) {
      String onePoint = "aeioulnstr";
      String twoPoints = "dg";
      String threePoints = "bcmp";
      String fourPoints = "fhvwy";
      String fivePoints = "k";
      String eightPoints = "jx";
      String tenPoints = "qz";

      if (onePoint.contains(Character.toString(letter))) {
         System.out.println("Expected score: 1");
      }

      if (twoPoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 2");
      }

      if (threePoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 3");
      }

      if (fourPoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 4");
      }

      if (fivePoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 5");
      }

      if (eightPoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 8");
      }

      if (tenPoints.contains(Character.toString(letter))) {
         System.out.println("Expected score: 10");
      }

      System.out.println();
   }
}

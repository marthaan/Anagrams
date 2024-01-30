// Name: Martha Ann Williams
// USC NetID: marthaan
// CS 455 PA4
// Fall 2023

/**
 * Contains information about how much each Scrabble letter/word is worth
 */
public class ScoreTable {

   /**
    * Representation invariant:
    *
    * A ScoreTable contains an integer array which holds the corresponding scores
    * for each letter in the English alphabet. MAX_LETTERS is a constant used to
    * declare the scores array size, since we are treating lower case and upper case
    * letters the same for score values.
    */
   private int[] scores;
   private static final int MAX_LETTERS = 26;

   /**
    * Creates a table of Scrabble scores associated
    * with each letter (lower or upper case) in the English alphabet.
    */
   public ScoreTable() {
      scores = new int[MAX_LETTERS];

      String onePoint = "aeioulnstr";
      assignScores(onePoint, 1);

      String twoPoints = "dg";
      assignScores(twoPoints, 2);

      String threePoints = "bcmp";
      assignScores(threePoints, 3);

      String fourPoints = "fhvwy";
      assignScores(fourPoints, 4);

      String fivePoints = "k";
      assignScores(fivePoints, 5);

      String eightPoints = "jx";
      assignScores(eightPoints, 8);

      String tenPoints = "qz";
      assignScores(tenPoints, 10);
   }

   /**
    * Gets the score of the given letter.
    * @param letter desired letter to get score of
    * @return score of the letter
    */
   public int getLetterScore(char letter) {
      if (Character.isUpperCase(letter)) {
         letter = Character.toLowerCase(letter);
      }

      return scores[letter - 'a'];
   }

   /**
    * Gets the score of the given String, using
    * the letter score of each Character in the String.
    * @param string desired String to get the score of
    * @return score of the word (string)
    */
   public int getWordScore(String string) {
      int wordScore = 0;

      for (Character letter : string.toCharArray()) {
         int letterScore = getLetterScore(letter);
         wordScore += letterScore;
      }

      return wordScore;
   }

   /**
    * Assigns the appropriate score to each Character in a String.
    * @param string string containing letters that have the same score
    * @param score score to be assigned
    */
   private void assignScores(String string, int score) {
      for (char letter : string.toCharArray()) {
         scores[letter - 'a'] = score;
      }
   }
}

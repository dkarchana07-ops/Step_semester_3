package week1.assigment_problems;

public class ReviewWordLengthProfiler {

    public static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");

        int shortWords = 0;   // 1-4 letters
        int mediumWords = 0;  // 5-8 letters
        int longWords = 0;    // 9+ letters

        for (String word : words) {
            // Remove punctuation characters to count only letters
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            int len = cleanWord.length();

            if (len >= 1 && len <= 4) {
                shortWords++;
            } else if (len >= 5 && len <= 8) {
                mediumWords++;
            } else if (len >= 9) {
                longWords++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortWords, mediumWords, longWords);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
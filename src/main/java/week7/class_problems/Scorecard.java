package week7.class_problems;

public class Scorecard {
    private final boolean[] answers;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        this.answers = new boolean[totalQuestions];
        this.recordedCount = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (recordedCount < answers.length) {
            answers[recordedCount++] = isCorrect;
        } else {
            System.out.println("Rejected: all question answers have already been recorded.");
        }
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (answers[i]) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("sc.getScore() -> " + sc.getScore());
    }
}
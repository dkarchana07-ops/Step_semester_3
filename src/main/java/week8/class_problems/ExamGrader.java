package week8.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;
    protected String studentAnswer;
    protected double points;

    public Question(String questionText, String correctAnswer, String studentAnswer, double points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    public abstract double evaluate();
    public abstract String getQuestionType();
}

class MCQQuestion extends Question {
    public MCQQuestion(String qText, String cAns, String sAns, double pts) {
        super(qText, cAns, sAns, pts);
    }

    @Override
    public double evaluate() {
        return studentAnswer.equals(correctAnswer) ? points : 0.0;
    }

    @Override
    public String getQuestionType() {
        return "MCQ";
    }
}

class TFQuestion extends Question {
    public TFQuestion(String qText, String cAns, String sAns, double pts) {
        super(qText, cAns, sAns, pts);
    }

    @Override
    public double evaluate() {
        return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0.0;
    }

    @Override
    public String getQuestionType() {
        return "TF";
    }
}

class EssayQuestion extends Question {
    public EssayQuestion(String qText, String cAns, String sAns, double pts) {
        super(qText, cAns, sAns, pts);
    }

    @Override
    public double evaluate() {
        String[] keywords = correctAnswer.split(",");
        int matched = 0;
        String studentLower = studentAnswer.toLowerCase();

        for (String kw : keywords) {
            String trimmedKw = kw.trim().toLowerCase();
            if (!trimmedKw.isEmpty() && studentLower.contains(trimmedKw)) {
                matched++;
            }
        }

        if (matched >= 2) {
            return 0.75 * points;
        } else if (matched == 1) {
            return 0.50 * points;
        }
        return 0.0;
    }

    @Override
    public String getQuestionType() {
        return "ESSAY";
    }
}

public class ExamGrader {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);

            List<String> quoted = new ArrayList<>();
            Matcher m = Pattern.compile("\"([^\"]*)\"").matcher(line);
            while (m.find()) {
                quoted.add(m.group(1));
            }

            int lastQuote = line.lastIndexOf('"');
            double pts = Double.parseDouble(line.substring(lastQuote + 1).trim());

            if (type.equalsIgnoreCase("MCQ")) {
                questions.add(new MCQQuestion(quoted.get(0), quoted.get(1), quoted.get(2), pts));
            } else if (type.equalsIgnoreCase("TF")) {
                questions.add(new TFQuestion(quoted.get(0), quoted.get(1), quoted.get(2), pts));
            } else if (type.equalsIgnoreCase("ESSAY")) {
                questions.add(new EssayQuestion(quoted.get(0), quoted.get(1), quoted.get(2), pts));
            }
        }

        double totalScore = 0.0;
        for (Question q : questions) {
            double score = q.evaluate();
            totalScore += score;
            System.out.printf(Locale.US, "%s: %.2f%n", q.getQuestionType(), score);
        }
        System.out.printf(Locale.US, "Total Score: %.2f%n", totalScore);
        sc.close();
    }
}
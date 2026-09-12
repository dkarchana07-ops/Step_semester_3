package week1.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    // Approach 1: Iterative two-pointer check
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Recursive check
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: Character array reversal
    public static boolean isPalindromeArrayReversal(String text) {
        char[] chars = text.toCharArray();
        char[] reversed = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }

        return text.equals(new String(reversed));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to verify: ");
        String text = scanner.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        String resultIter = iterative ? "Palindrome" : "Not Palindrome";
        String resultRec = recursive ? "Palindrome" : "Not Palindrome";
        String resultArr = arrayReversal ? "Palindrome" : "Not Palindrome";

        System.out.println("Iterative: " + resultIter + " | Recursive: " + resultRec + " | Array Reversal: " + resultArr);

        scanner.close();
    }
}
package week7.assigment_problems;

class PasswordChecker {
    private final String password;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public String getStrength() {
        if (password == null || password.length() < 6) {
            return "Weak";
        } else if (password.length() <= 9) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}

public class PasswordSecurityChecker {
    public static void main(String[] args) {
        PasswordChecker pc = new PasswordChecker("abcd");
        System.out.println("pc.getStrength() -> " + pc.getStrength());

        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("pc2.getStrength() -> " + pc2.getStrength());
    }
}
package week8.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract double calculateAdjustedAmount();
    public abstract String getPaymentType();
}

class CardPayment extends Payment {
    public CardPayment(double amount) {
        super(amount);
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount * 1.02;
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}

class WalletPayment extends Payment {
    public WalletPayment(double amount) {
        super(amount);
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount * 1.01;
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }
}

class BankTransferPayment extends Payment {
    public BankTransferPayment(double amount) {
        super(amount);
    }

    @Override
    public double calculateAdjustedAmount() {
        return amount;
    }

    @Override
    public String getPaymentType() {
        return "BANKTRANSFER";
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Payment> transactions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amt = sc.nextDouble();

            if (type.equalsIgnoreCase("CARD")) {
                transactions.add(new CardPayment(amt));
            } else if (type.equalsIgnoreCase("WALLET")) {
                transactions.add(new WalletPayment(amt));
            } else if (type.equalsIgnoreCase("BANKTRANSFER")) {
                transactions.add(new BankTransferPayment(amt));
            }
        }

        double total = 0.0;
        for (Payment p : transactions) {
            double adjusted = p.calculateAdjustedAmount();
            total += adjusted;
            System.out.printf(Locale.US, "%s: %.2f%n", p.getPaymentType(), adjusted);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
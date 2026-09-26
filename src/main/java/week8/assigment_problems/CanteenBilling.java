package week8.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Customer {
    protected double amount;

    public Customer(double amount) {
        this.amount = amount;
    }

    public abstract double calculateFinalAmount();
    public abstract String getCustomerType();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double amount) {
        super(amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 0.90;
    }

    @Override
    public String getCustomerType() {
        return "STUDENT";
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double amount) {
        super(amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount * 0.95;
    }

    @Override
    public String getCustomerType() {
        return "STAFF";
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double amount) {
        super(amount);
    }

    @Override
    public double calculateFinalAmount() {
        return amount + 10.0;
    }

    @Override
    public String getCustomerType() {
        return "GUEST";
    }
}

public class CanteenBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Customer> bills = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amt = sc.nextDouble();

            if (type.equalsIgnoreCase("STUDENT")) {
                bills.add(new StudentCustomer(amt));
            } else if (type.equalsIgnoreCase("STAFF")) {
                bills.add(new StaffCustomer(amt));
            } else if (type.equalsIgnoreCase("GUEST")) {
                bills.add(new GuestCustomer(amt));
            }
        }

        double grandTotal = 0.0;
        for (Customer c : bills) {
            double finalAmount = c.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf(Locale.US, "%s: %.2f%n", c.getCustomerType(), finalAmount);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        sc.close();
    }
}
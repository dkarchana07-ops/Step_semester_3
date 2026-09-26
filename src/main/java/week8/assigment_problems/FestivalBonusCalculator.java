package week8.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Employee {
    protected String name;
    protected double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public abstract double calculateBonus();

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return 2000.0;
    }
}

public class FestivalBonusCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();

            if (type.equalsIgnoreCase("FULLTIME")) {
                employees.add(new FullTimeEmployee(name, salary));
            } else if (type.equalsIgnoreCase("PARTTIME")) {
                employees.add(new PartTimeEmployee(name, salary));
            } else if (type.equalsIgnoreCase("INTERN")) {
                employees.add(new InternEmployee(name, salary));
            }
        }

        double totalBonus = 0.0;
        for (Employee e : employees) {
            double bonus = e.calculateBonus();
            totalBonus += bonus;
            System.out.printf(Locale.US, "%s: %.2f%n", e.getName(), bonus);
        }
        System.out.printf(Locale.US, "Total Bonus: %.2f%n", totalBonus);
        sc.close();
    }
}
package week9.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Staff {
    protected String name;

    public Staff(String name) {
        this.name = name;
    }

    public abstract double calculatePay();

    public String getName() {
        return name;
    }
}

class FullTimeStaff extends Staff {
    private double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        return weeklySalary;
    }
}

class HourlyStaff extends Staff {
    private double hours;
    private double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        if (hours <= 40) {
            return hours * rate;
        }
        return (40 * rate) + ((hours - 40) * 1.5 * rate);
    }
}

class InternStaff extends Staff {
    private double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Staff> staffList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();

            if (type.equalsIgnoreCase("FULLTIME")) {
                double salary = sc.nextDouble();
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equalsIgnoreCase("HOURLY")) {
                double hours = sc.nextDouble();
                double rate = sc.nextDouble();
                staffList.add(new HourlyStaff(name, hours, rate));
            } else if (type.equalsIgnoreCase("INTERN")) {
                double stipend = sc.nextDouble();
                staffList.add(new InternStaff(name, stipend));
            }
        }

        double totalPayroll = 0.0;
        for (Staff s : staffList) {
            double pay = s.calculatePay();
            totalPayroll += pay;
            System.out.printf(Locale.US, "%s: %.2f%n", s.getName(), pay);
        }
        System.out.printf(Locale.US, "Total Payroll: %.2f%n", totalPayroll);
        sc.close();
    }
}
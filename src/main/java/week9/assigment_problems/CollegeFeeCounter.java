package week9.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface BusUser {
    default double getTransportFee() {
        return 12000.0;
    }
}

abstract class Student {
    protected String name;

    public Student(String name) {
        this.name = name;
    }

    public abstract double calculateTuitionAndCollegeFee();

    public double calculateTotalFee() {
        double fee = calculateTuitionAndCollegeFee();
        if (this instanceof BusUser) {
            fee += ((BusUser) this).getTransportFee();
        }
        return fee;
    }

    public String getName() {
        return name;
    }
}

class DayScholarStudent extends Student implements BusUser {
    public DayScholarStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuitionAndCollegeFee() {
        return 40000.0;
    }
}

class HostellerStudent extends Student {
    public HostellerStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuitionAndCollegeFee() {
        return 40000.0 + 60000.0;
    }
}

class ScholarStudent extends Student implements BusUser {
    public ScholarStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuitionAndCollegeFee() {
        return 20000.0;
    }
}

public class CollegeFeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();

            if (type.equalsIgnoreCase("DAY_SCHOLAR")) {
                students.add(new DayScholarStudent(name));
            } else if (type.equalsIgnoreCase("HOSTELLER")) {
                students.add(new HostellerStudent(name));
            } else if (type.equalsIgnoreCase("SCHOLAR")) {
                students.add(new ScholarStudent(name));
            }
        }

        double totalCollected = 0.0;
        for (Student s : students) {
            double fee = s.calculateTotalFee();
            totalCollected += fee;
            System.out.printf(Locale.US, "%s: %.2f%n", s.getName(), fee);
        }
        System.out.printf(Locale.US, "Total Collected: %.2f%n", totalCollected);
        sc.close();
    }
}
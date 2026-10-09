package week9.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class LibraryItem {
    protected String title;
    protected int daysLate;

    public LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public abstract double calculateFine();

    public String getTitle() {
        return title;
    }
}

class BookItem extends LibraryItem {
    public BookItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 2.0;
    }
}

class DVDItem extends LibraryItem {
    public DVDItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return Math.min(50.0, daysLate * 5.0);
    }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        return daysLate * 1.0;
    }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<LibraryItem> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String title = sc.next();
            int days = sc.nextInt();

            if (type.equalsIgnoreCase("BOOK")) {
                items.add(new BookItem(title, days));
            } else if (type.equalsIgnoreCase("DVD")) {
                items.add(new DVDItem(title, days));
            } else if (type.equalsIgnoreCase("MAGAZINE")) {
                items.add(new MagazineItem(title, days));
            }
        }

        double totalFines = 0.0;
        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            totalFines += fine;
            System.out.printf(Locale.US, "%s: %.2f%n", item.getTitle(), fine);
        }
        System.out.printf(Locale.US, "Total Fines: %.2f%n", totalFines);
        sc.close();
    }
}
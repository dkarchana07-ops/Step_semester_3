package week9.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class ElectricityConnection {
    protected int units;

    public ElectricityConnection(int units) {
        this.units = units;
    }

    public abstract double calculateBill();
    public abstract String getType();
}

class HomeConnection extends ElectricityConnection {
    public HomeConnection(int units) {
        super(units);
    }

    @Override
    public double calculateBill() {
        if (units <= 100) {
            return units * 5.0;
        }
        return (100 * 5.0) + ((units - 100) * 7.0);
    }

    @Override
    public String getType() {
        return "HOME";
    }
}

class ShopConnection extends ElectricityConnection {
    public ShopConnection(int units) {
        super(units);
    }

    @Override
    public double calculateBill() {
        return (units * 8.0) + 100.0;
    }

    @Override
    public String getType() {
        return "SHOP";
    }
}

class FactoryConnection extends ElectricityConnection {
    public FactoryConnection(int units) {
        super(units);
    }

    @Override
    public double calculateBill() {
        return Math.max(1000.0, units * 6.0);
    }

    @Override
    public String getType() {
        return "FACTORY";
    }
}

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<ElectricityConnection> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();

            if (type.equalsIgnoreCase("HOME")) {
                connections.add(new HomeConnection(units));
            } else if (type.equalsIgnoreCase("SHOP")) {
                connections.add(new ShopConnection(units));
            } else if (type.equalsIgnoreCase("FACTORY")) {
                connections.add(new FactoryConnection(units));
            }
        }

        double total = 0.0;
        for (ElectricityConnection conn : connections) {
            double bill = conn.calculateBill();
            total += bill;
            System.out.printf(Locale.US, "%s: %.2f%n", conn.getType(), bill);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
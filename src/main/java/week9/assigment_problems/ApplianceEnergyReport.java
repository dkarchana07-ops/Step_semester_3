package week9.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface SaverCapable {}

abstract class Appliance {
    protected double hours;
    protected boolean isSaverRequested;

    public Appliance(double hours, boolean isSaverRequested) {
        this.hours = hours;
        this.isSaverRequested = isSaverRequested;
    }

    public abstract double getPowerRating();
    public abstract String getName();

    public boolean isValid() {
        if (isSaverRequested && !(this instanceof SaverCapable)) {
            return false;
        }
        return true;
    }

    public double calculateUnits() {
        double units = (getPowerRating() * hours) / 1000.0;
        if (isSaverRequested && (this instanceof SaverCapable)) {
            units *= 0.75;
        }
        return units;
    }

    public double calculateCost() {
        return calculateUnits() * 8.0;
    }
}

class Fridge extends Appliance {
    public Fridge(double hours, boolean isSaver) {
        super(hours, isSaver);
    }

    @Override
    public double getPowerRating() {
        return 150.0;
    }

    @Override
    public String getName() {
        return "FRIDGE";
    }
}

class AC extends Appliance implements SaverCapable {
    public AC(double hours, boolean isSaver) {
        super(hours, isSaver);
    }

    @Override
    public double getPowerRating() {
        return 1500.0;
    }

    @Override
    public String getName() {
        return "AC";
    }
}

class TV extends Appliance {
    public TV(double hours, boolean isSaver) {
        super(hours, isSaver);
    }

    @Override
    public double getPowerRating() {
        return 100.0;
    }

    @Override
    public String getName() {
        return "TV";
    }
}

class Washer extends Appliance implements SaverCapable {
    public Washer(double hours, boolean isSaver) {
        super(hours, isSaver);
    }

    @Override
    public double getPowerRating() {
        return 500.0;
    }

    @Override
    public String getName() {
        return "WASHER";
    }
}

public class ApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Appliance> appliances = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split("\\s+");
            String type = tokens[0];
            double hours = Double.parseDouble(tokens[1]);
            boolean isSaver = tokens.length > 2 && tokens[2].equalsIgnoreCase("SAVER");

            if (type.equalsIgnoreCase("FRIDGE")) {
                appliances.add(new Fridge(hours, isSaver));
            } else if (type.equalsIgnoreCase("AC")) {
                appliances.add(new AC(hours, isSaver));
            } else if (type.equalsIgnoreCase("TV")) {
                appliances.add(new TV(hours, isSaver));
            } else if (type.equalsIgnoreCase("WASHER")) {
                appliances.add(new Washer(hours, isSaver));
            }
        }

        double totalCost = 0.0;
        for (Appliance app : appliances) {
            if (!app.isValid()) {
                System.out.printf("%s: saver mode not supported%n", app.getName());
            } else {
                double units = app.calculateUnits();
                double cost = app.calculateCost();
                totalCost += cost;
                System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n", app.getName(), units, cost);
            }
        }
        System.out.printf(Locale.US, "Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
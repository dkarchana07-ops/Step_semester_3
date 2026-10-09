package week9.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface NightServiceable {}

abstract class Cab {
    protected double km;
    protected String time;

    public Cab(double km, String time) {
        this.km = km;
        this.time = time;
    }

    public abstract double getRatePerKm();
    public abstract String getCabType();

    public boolean isTripValid() {
        if (time.equalsIgnoreCase("NIGHT") && !(this instanceof NightServiceable)) {
            return false;
        }
        return true;
    }

    public double calculateFare() {
        double baseFare = Math.max(100.0, km * getRatePerKm());
        if (time.equalsIgnoreCase("NIGHT")) {
            baseFare *= 1.20;
        }
        return baseFare;
    }
}

class MiniCab extends Cab {
    public MiniCab(double km, String time) {
        super(km, time);
    }

    @Override
    public double getRatePerKm() {
        return 10.0;
    }

    @Override
    public String getCabType() {
        return "MINI";
    }
}

class SedanCab extends Cab implements NightServiceable {
    public SedanCab(double km, String time) {
        super(km, time);
    }

    @Override
    public double getRatePerKm() {
        return 14.0;
    }

    @Override
    public String getCabType() {
        return "SEDAN";
    }
}

class SUVCab extends Cab implements NightServiceable {
    public SUVCab(double km, String time) {
        super(km, time);
    }

    @Override
    public double getRatePerKm() {
        return 18.0;
    }

    @Override
    public String getCabType() {
        return "SUV";
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Cab> trips = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();

            if (type.equalsIgnoreCase("MINI")) {
                trips.add(new MiniCab(km, time));
            } else if (type.equalsIgnoreCase("SEDAN")) {
                trips.add(new SedanCab(km, time));
            } else if (type.equalsIgnoreCase("SUV")) {
                trips.add(new SUVCab(km, time));
            }
        }

        double grandTotal = 0.0;
        for (Cab cab : trips) {
            if (!cab.isTripValid()) {
                System.out.printf("%s: night service not available%n", cab.getCabType());
            } else {
                double fare = cab.calculateFare();
                grandTotal += fare;
                System.out.printf(Locale.US, "%s: %.2f%n", cab.getCabType(), fare);
            }
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        sc.close();
    }
}
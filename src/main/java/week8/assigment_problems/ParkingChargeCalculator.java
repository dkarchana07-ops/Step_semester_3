package week8.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Vehicle {
    protected int hours;

    public Vehicle(int hours) {
        this.hours = hours;
    }

    public abstract double calculateCharge();
    public abstract String getVehicleType();
}

class Bike extends Vehicle {
    public Bike(int hours) {
        super(hours);
    }

    @Override
    public double calculateCharge() {
        return hours * 10.0;
    }

    @Override
    public String getVehicleType() {
        return "BIKE";
    }
}

class Car extends Vehicle {
    public Car(int hours) {
        super(hours);
    }

    @Override
    public double calculateCharge() {
        if (hours <= 1) {
            return 30.0;
        }
        return 30.0 + (hours - 1) * 20.0;
    }

    @Override
    public String getVehicleType() {
        return "CAR";
    }
}

class Truck extends Vehicle {
    public Truck(int hours) {
        super(hours);
    }

    @Override
    public double calculateCharge() {
        return Math.max(100.0, hours * 50.0);
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }
}

public class ParkingChargeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hrs = sc.nextInt();

            if (type.equalsIgnoreCase("BIKE")) {
                vehicles.add(new Bike(hrs));
            } else if (type.equalsIgnoreCase("CAR")) {
                vehicles.add(new Car(hrs));
            } else if (type.equalsIgnoreCase("TRUCK")) {
                vehicles.add(new Truck(hrs));
            }
        }

        double grandTotal = 0.0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            grandTotal += charge;
            System.out.printf(Locale.US, "%s: %.2f%n", v.getVehicleType(), charge);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        sc.close();
    }
}
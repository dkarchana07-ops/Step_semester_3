package week9.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class TravelBooking {
    protected double distanceKm;
    public static final double BOOKING_FEE = 50.0;

    public TravelBooking(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public abstract double calculateBaseFare();
    public abstract String getMode();

    public double calculateTotalFare() {
        return calculateBaseFare() + BOOKING_FEE;
    }
}

class BusBooking extends TravelBooking {
    public BusBooking(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return 2.0 * distanceKm;
    }

    @Override
    public String getMode() {
        return "BUS";
    }
}

class TrainBooking extends TravelBooking {
    public TrainBooking(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return 1.5 * distanceKm;
    }

    @Override
    public String getMode() {
        return "TRAIN";
    }
}

class FlightBooking extends TravelBooking {
    public FlightBooking(double distanceKm) {
        super(distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        return 2500.0 + (4.0 * distanceKm);
    }

    @Override
    public String getMode() {
        return "FLIGHT";
    }
}

public class TravelBookingFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<TravelBooking> bookings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String mode = sc.next();
            double dist = sc.nextDouble();

            if (mode.equalsIgnoreCase("BUS")) {
                bookings.add(new BusBooking(dist));
            } else if (mode.equalsIgnoreCase("TRAIN")) {
                bookings.add(new TrainBooking(dist));
            } else if (mode.equalsIgnoreCase("FLIGHT")) {
                bookings.add(new FlightBooking(dist));
            }
        }

        for (TravelBooking b : bookings) {
            System.out.printf(Locale.US, "%s: %.2f%n", b.getMode(), b.calculateTotalFare());
        }
        sc.close();
    }
}
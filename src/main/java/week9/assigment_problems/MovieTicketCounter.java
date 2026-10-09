package week9.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class TicketBooking {
    protected int count;
    public static final double CONVENIENCE_FEE = 20.0;

    public TicketBooking(int count) {
        this.count = count;
    }

    public abstract double getPricePerTicket();
    public abstract String getSeatType();

    public double calculateTotalAmount() {
        return count * (getPricePerTicket() + CONVENIENCE_FEE);
    }
}

class RegularTicket extends TicketBooking {
    public RegularTicket(int count) {
        super(count);
    }

    @Override
    public double getPricePerTicket() {
        return 150.0;
    }

    @Override
    public String getSeatType() {
        return "REGULAR";
    }
}

class PremiumTicket extends TicketBooking {
    public PremiumTicket(int count) {
        super(count);
    }

    @Override
    public double getPricePerTicket() {
        return 250.0;
    }

    @Override
    public String getSeatType() {
        return "PREMIUM";
    }
}

class ReclinerTicket extends TicketBooking {
    public ReclinerTicket(int count) {
        super(count);
    }

    @Override
    public double getPricePerTicket() {
        return 400.0;
    }

    @Override
    public String getSeatType() {
        return "RECLINER";
    }
}

public class MovieTicketCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<TicketBooking> bookings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();

            if (seat.equalsIgnoreCase("REGULAR")) {
                bookings.add(new RegularTicket(count));
            } else if (seat.equalsIgnoreCase("PREMIUM")) {
                bookings.add(new PremiumTicket(count));
            } else if (seat.equalsIgnoreCase("RECLINER")) {
                bookings.add(new ReclinerTicket(count));
            }
        }

        double total = 0.0;
        for (TicketBooking b : bookings) {
            double amt = b.calculateTotalAmount();
            total += amt;
            System.out.printf(Locale.US, "%s: %.2f%n", b.getSeatType(), amt);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
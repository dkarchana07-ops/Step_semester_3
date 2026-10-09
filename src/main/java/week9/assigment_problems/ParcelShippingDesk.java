package week9.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface Insurable {
    double calculateInsurance();
}

abstract class Parcel {
    protected double weightKg;
    protected double declaredValue;

    public Parcel(double weightKg, double declaredValue) {
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public abstract double calculateShippingCharge();
    public abstract String getType();

    public double getInsuranceAmount() {
        if (this instanceof Insurable) {
            return ((Insurable) this).calculateInsurance();
        }
        return 0.0;
    }

    public double calculateTotal() {
        return calculateShippingCharge() + getInsuranceAmount();
    }
}

class StandardParcel extends Parcel {
    public StandardParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 40.0 + (10.0 * weightKg);
    }

    @Override
    public String getType() {
        return "STANDARD";
    }
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return 80.0 + (15.0 * weightKg);
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * declaredValue;
    }

    @Override
    public String getType() {
        return "EXPRESS";
    }
}

class FragileParcel extends Parcel implements Insurable {
    public FragileParcel(double weightKg, double declaredValue) {
        super(weightKg, declaredValue);
    }

    @Override
    public double calculateShippingCharge() {
        return (40.0 + 10.0 * weightKg) + 50.0;
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * declaredValue;
    }

    @Override
    public String getType() {
        return "FRAGILE";
    }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<Parcel> parcels = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();

            if (type.equalsIgnoreCase("STANDARD")) {
                parcels.add(new StandardParcel(weight, value));
            } else if (type.equalsIgnoreCase("EXPRESS")) {
                parcels.add(new ExpressParcel(weight, value));
            } else if (type.equalsIgnoreCase("FRAGILE")) {
                parcels.add(new FragileParcel(weight, value));
            }
        }

        double grandTotal = 0.0;
        for (Parcel p : parcels) {
            double charge = p.calculateShippingCharge();
            double insurance = p.getInsuranceAmount();
            double total = p.calculateTotal();
            grandTotal += total;
            System.out.printf(Locale.US, "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    p.getType(), charge, insurance, total);
        }
        System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
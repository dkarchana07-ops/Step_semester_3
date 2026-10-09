package week9.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class GardenPlot {
    protected String owner;

    public GardenPlot(String owner) {
        this.owner = owner;
    }

    public abstract double calculateArea();
    public abstract String getShapeName();

    public String getOwner() {
        return owner;
    }
}

class CirclePlot extends GardenPlot {
    private double radius;

    public CirclePlot(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getShapeName() {
        return "CIRCLE";
    }
}

class RectanglePlot extends GardenPlot {
    private double length;
    private double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public String getShapeName() {
        return "RECTANGLE";
    }
}

class TrianglePlot extends GardenPlot {
    private double base;
    private double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public String getShapeName() {
        return "TRIANGLE";
    }
}

public class GardenPlotReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<GardenPlot> plots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String shape = sc.next();
            String owner = sc.next();

            if (shape.equalsIgnoreCase("CIRCLE")) {
                double radius = sc.nextDouble();
                plots.add(new CirclePlot(owner, radius));
            } else if (shape.equalsIgnoreCase("RECTANGLE")) {
                double length = sc.nextDouble();
                double width = sc.nextDouble();
                plots.add(new RectanglePlot(owner, length, width));
            } else if (shape.equalsIgnoreCase("TRIANGLE")) {
                double base = sc.nextDouble();
                double height = sc.nextDouble();
                plots.add(new TrianglePlot(owner, base, height));
            }
        }

        double totalArea = 0.0;
        for (GardenPlot plot : plots) {
            double area = plot.calculateArea();
            totalArea += area;
            System.out.printf(Locale.US, "%s (%s): %.2f%n", plot.getOwner(), plot.getShapeName(), area);
        }
        System.out.printf(Locale.US, "Total Area: %.2f%n", totalArea);
        sc.close();
    }
}
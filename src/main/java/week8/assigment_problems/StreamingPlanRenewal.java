package week8.assigment_problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SubscriptionPlan {
    protected String name;
    protected LocalDate startDate;

    public SubscriptionPlan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public abstract LocalDate calculateRenewalDate();

    public String getName() {
        return name;
    }
}

class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(30);
    }
}

class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(90);
    }
}

class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(365);
    }
}

public class StreamingPlanRenewal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        List<SubscriptionPlan> subscriptions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String planType = sc.next();
            String name = sc.next();
            String dateStr = sc.next();
            LocalDate date = LocalDate.parse(dateStr);

            if (planType.equalsIgnoreCase("BASIC")) {
                subscriptions.add(new BasicPlan(name, date));
            } else if (planType.equalsIgnoreCase("STANDARD")) {
                subscriptions.add(new StandardPlan(name, date));
            } else if (planType.equalsIgnoreCase("PREMIUM")) {
                subscriptions.add(new PremiumPlan(name, date));
            }
        }

        for (SubscriptionPlan sub : subscriptions) {
            System.out.println(sub.getName() + ": " + sub.calculateRenewalDate());
        }
        sc.close();
    }
}
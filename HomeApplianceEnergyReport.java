import java.util.*;

abstract class Appliance {
    double hours;
    static final double COST_PER_UNIT = 8;

    Appliance(double hours) {
        this.hours = hours;
    }

    abstract double getPower();

    double getUnits() {
        return getPower() * hours / 1000;
    }

    double getCost() {
        return getUnits() * COST_PER_UNIT;
    }
}

interface SaverMode {
    double getSaverUnits();
}

class Fridge extends Appliance {
    Fridge(double hours) {
        super(hours);
    }

    double getPower() {
        return 150;
    }
}

class AC extends Appliance implements SaverMode {
    AC(double hours) {
        super(hours);
    }

    double getPower() {
        return 1500;
    }

    public double getSaverUnits() {
        return getUnits() * 0.75;
    }
}

class TV extends Appliance {
    TV(double hours) {
        super(hours);
    }

    double getPower() {
        return 100;
    }
}

class Washer extends Appliance implements SaverMode {
    Washer(double hours) {
        super(hours);
    }

    double getPower() {
        return 500;
    }

    public double getSaverUnits() {
        return getUnits() * 0.75;
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalCost = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double hours = sc.nextDouble();

            boolean saver = false;

            // The third input token is optional.
            if (sc.hasNext("SAVER")) {
                sc.next();
                saver = true;
            }

            Appliance appliance = null;

            switch (type) {
                case "FRIDGE":
                    appliance = new Fridge(hours);
                    break;
                case "AC":
                    appliance = new AC(hours);
                    break;
                case "TV":
                    appliance = new TV(hours);
                    break;
                case "WASHER":
                    appliance = new Washer(hours);
                    break;
            }

            if (saver && !(appliance instanceof SaverMode)) {
                System.out.println(
                    type + ": saver mode not supported");
                continue;
            }

            double units = appliance.getUnits();

            if (saver) {
                units = ((SaverMode) appliance).getSaverUnits();
            }

            double cost = units * Appliance.COST_PER_UNIT;

            System.out.printf(
                "%s: Units=%.2f Cost=%.2f%n",
                type, units, cost);

            totalCost += cost;
        }

        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}

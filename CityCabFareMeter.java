import java.util.*;

abstract class Cab {
    double km;

    Cab(double km) {
        this.km = km;
    }

    abstract double getRate();

    double getFare() {
        return Math.max(100, km * getRate());
    }
}

interface NightService {
    double nightFare();
}

class Mini extends Cab {
    Mini(double km) {
        super(km);
    }

    double getRate() {
        return 10;
    }
}

class Sedan extends Cab implements NightService {
    Sedan(double km) {
        super(km);
    }

    double getRate() {
        return 14;
    }

    public double nightFare() {
        return getFare() * 1.20;
    }
}

class SUV extends Cab implements NightService {
    SUV(double km) {
        super(km);
    }

    double getRate() {
        return 18;
    }

    public double nightFare() {
        return getFare() * 1.20;
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase();

            Cab cab = null;

            switch (type) {
                case "MINI":
                    cab = new Mini(km);
                    break;
                case "SEDAN":
                    cab = new Sedan(km);
                    break;
                case "SUV":
                    cab = new SUV(km);
                    break;
            }

            if (time.equals("NIGHT") && !(cab instanceof NightService)) {
                System.out.println(
                    type + ": night service not available");
                continue;
            }

            double fare = cab.getFare();

            if (time.equals("NIGHT")) {
                fare = ((NightService) cab).nightFare();
            }

            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}

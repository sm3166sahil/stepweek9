import java.util.*;

abstract class Booking {
    static final double BOOKING_FEE = 50;
    double distance;

    Booking(double distance) {
        this.distance = distance;
    }

    abstract double getFare();

    double getTotal() {
        return getFare() + BOOKING_FEE;
    }
}

class Bus extends Booking {
    Bus(double distance) {
        super(distance);
    }

    double getFare() {
        return distance * 2;
    }
}

class Train extends Booking {
    Train(double distance) {
        super(distance);
    }

    double getFare() {
        return distance * 1.5;
    }
}

class Flight extends Booking {
    Flight(double distance) {
        super(distance);
    }

    double getFare() {
        return 2500 + distance * 4;
    }
}

public class TravelBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Booking[] bookings = new Booking[n];
        String[] modes = new String[n];

        for (int i = 0; i < n; i++) {
            modes[i] = sc.next().toUpperCase();
            double distance = sc.nextDouble();

            switch (modes[i]) {
                case "BUS":
                    bookings[i] = new Bus(distance);
                    break;
                case "TRAIN":
                    bookings[i] = new Train(distance);
                    break;
                case "FLIGHT":
                    bookings[i] = new Flight(distance);
                    break;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("%s: %.2f%n",
                modes[i], bookings[i].getTotal());
        }

        sc.close();
    }
}

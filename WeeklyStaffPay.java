import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract double getPay();
}

class FullTime extends Staff {
    double salary;

    FullTime(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    double getPay() {
        return salary;
    }
}

class Hourly extends Staff {
    double hours, rate;

    Hourly(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double getPay() {
        if (hours <= 40)
            return hours * rate;

        return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class Intern extends Staff {
    double stipend;

    Intern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double getPay() {
        return stipend;
    }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Staff[] staff = new Staff[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();

            switch (type) {
                case "FULLTIME":
                    staff[i] = new FullTime(name, sc.nextDouble());
                    break;
                case "HOURLY":
                    staff[i] = new Hourly(
                        name, sc.nextDouble(), sc.nextDouble());
                    break;
                case "INTERN":
                    staff[i] = new Intern(name, sc.nextDouble());
                    break;
            }
        }

        for (Staff s : staff) {
            double pay = s.getPay();
            System.out.printf("%s: %.2f%n", s.name, pay);
            total += pay;
        }

        System.out.printf("Total Payroll: %.2f%n", total);
        sc.close();
    }
}

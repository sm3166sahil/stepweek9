import java.util.*;

abstract class Plot {
    String owner;

    Plot(String owner) {
        this.owner = owner;
    }

    abstract double getArea();

    abstract String getShape();
}

class CirclePlot extends Plot {
    double radius;

    CirclePlot(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }

    String getShape() {
        return "CIRCLE";
    }
}

class RectanglePlot extends Plot {
    double length, width;

    RectanglePlot(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    double getArea() {
        return length * width;
    }

    String getShape() {
        return "RECTANGLE";
    }
}

class TrianglePlot extends Plot {
    double base, height;

    TrianglePlot(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    double getArea() {
        return 0.5 * base * height;
    }

    String getShape() {
        return "TRIANGLE";
    }
}

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Plot[] plots = new Plot[n];
        double total = 0;

        for (int i = 0; i < n; i++) {
            String shape = sc.next().toUpperCase();
            String owner = sc.next();

            switch (shape) {
                case "CIRCLE":
                    plots[i] = new CirclePlot(owner, sc.nextDouble());
                    break;
                case "RECTANGLE":
                    plots[i] = new RectanglePlot(
                        owner, sc.nextDouble(), sc.nextDouble());
                    break;
                case "TRIANGLE":
                    plots[i] = new TrianglePlot(
                        owner, sc.nextDouble(), sc.nextDouble());
                    break;
            }
        }

        for (Plot p : plots) {
            double area = p.getArea();
            System.out.printf("%s (%s): %.2f%n",
                p.owner, p.getShape(), area);
            total += area;
        }

        System.out.printf("Total Area: %.2f%n", total);
        sc.close();
    }
}

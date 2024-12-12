public class TrianglePointChecker {
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double calculateTriangleArea(Point a, Point b, Point c) {
        return Math.abs((a.x * (b.y - c.y) + 
                         b.x * (c.y - a.y) + 
                         c.x * (a.y - b.y)) / 2.0);
    }

    public static boolean isPointInsideTriangle(Point a, Point b, Point c, Point p) {
        double totalArea = calculateTriangleArea(a, b, c);

        double area1 = calculateTriangleArea(p, a, b);
        double area2 = calculateTriangleArea(p, b, c);
        double area3 = calculateTriangleArea(p, c, a);

        return Math.abs(totalArea - (area1 + area2 + area3)) < 1e-10;
    }

    public static boolean isPointInsideTriangleRecursive(Point[] points) {
        if (points == null || points.length != 4) {
            throw new IllegalArgumentException("Input must contain 4 points: A, B, C, and P");
        }

        return isPointInsideTriangle(points[0], points[1], points[2], points[3]);
    }

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(5, 5);

        Point insidePoint = new Point(5, 2);
        Point outsidePoint = new Point(15, 10);
        Point onEdgePoint = new Point(5, 0);

        System.out.println("Non-Recursive Method:");
        System.out.println("Is inside point inside? " + 
            isPointInsideTriangle(a, b, c, insidePoint));
        System.out.println("Is outside point inside? " + 
            isPointInsideTriangle(a, b, c, outsidePoint));
        System.out.println("Is edge point inside? " + 
            isPointInsideTriangle(a, b, c, onEdgePoint));

        System.out.println("\nRecursive Method:");
        Point[] trianglePointsInside = {a, b, c, insidePoint};
        Point[] trianglePointsOutside = {a, b, c, outsidePoint};
        Point[] trianglePointsOnEdge = {a, b, c, onEdgePoint};

        System.out.println("Is inside point inside? " + 
            isPointInsideTriangleRecursive(trianglePointsInside));
        System.out.println("Is outside point inside? " + 
            isPointInsideTriangleRecursive(trianglePointsOutside));
        System.out.println("Is edge point inside? " + 
            isPointInsideTriangleRecursive(trianglePointsOnEdge));
    }
}

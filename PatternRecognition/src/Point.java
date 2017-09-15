import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }


    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "( " + x + "," + y + " )";
    }

    public int compareTo(Point that) {
        if (that.y < this.y) return 1;
        else if (that.y == this.y && that.x < this.x) return 1;
        else if (that.y == this.y && that.x == this.x) return 0;
        else return -1;
    }

    public double slopeTo(Point that) {//x.slopeTo(y),求得x->y的斜率
        int deltay = that.y - this.y;
        int deltax = that.x - this.x;
        if (deltay == 0 && deltax == 0) return Double.MIN_VALUE;
        else if (deltax == 0) return Double.MAX_VALUE;
        else if (deltay == 0) return 0.0;
        else return (double) deltay / deltax;
    }

    public Comparator<Point> slopeOrder() {
        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (slopeTo(o1) - slopeTo(o2));
            }
        };
        return comparator;

    }

    public static void main(String[] args) {
        Point point1 = new Point(5, 10);
        Point point2 = new Point(2, 2);
        System.out.println(point1.slopeTo(point2));

        point1.draw();
        point1.drawTo(point2);
    }
}

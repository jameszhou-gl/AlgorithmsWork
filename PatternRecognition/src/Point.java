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

    public int compareTo(Point that) {//x.compareTo(y)，ifx<y,return -1;
        if (this.y < that.y) return 1;
        else if (this.y == that.y && this.x < that.x) return 1;
        else if (this.y == that.y && this.x == that.x) return 0;
        else return -1;
    }

    public double slopeTo(Point that) {//x.slopeTo(y),求得x->y的斜率
        int deltay = that.y - this.y;
        int deltax = that.x - this.x;
        if (deltay == 0 && deltax == 0) return Double.NEGATIVE_INFINITY;
        else if (deltax == 0) return Double.POSITIVE_INFINITY;
        else if (deltay == 0) return 0.0;
        else return (double) deltay / deltax;
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }
    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            double slope1=slopeTo(o1);
            double slope2=slopeTo(o2);
            if (slope1<slope2)
                return -1;
            else if (slope1>slope2)
                return 1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        Point point1 = new Point(5, 10);
        Point point2 = new Point(2, 2);
        System.out.println(point1.slopeTo(point2));

        point1.draw();
        point1.drawTo(point2);
    }
}

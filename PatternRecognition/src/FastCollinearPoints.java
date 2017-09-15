import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private Point[] points;

    public FastCollinearPoints(Point[] points) {
        this.points = points;
    }

    //    public void sort(Point[] points){
//        Arrays.sort(points, new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                return (int) o1.slopeTo(o2);
//            }
//        });
//    }
    public int numberOfSegments() {
        int N = points.length;
        double[] slope = new double[N - 1];
        int count = 0;
        for (int i = 0; i < 1; i++) {
            Arrays.sort(points, points[0].slopeOrder());

        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        fastCollinearPoints.numberOfSegments();
//        for (LineSegment segment:fastCollinearPoints.segments()){
//            StdOut.println(segment);
//        }
    }
}

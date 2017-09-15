import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;

public class BruteCollinearPoints {
    private Point[] points;

    public BruteCollinearPoints(Point[] points) {
        this.points = points;
    }

    public int numberOfSegments() {
        int number = 0;
        int N = points.length;
        for (int p = 0; p < N; p++) {
            for (int q = p + 1; q < N; q++) {
                for (int r = q + 1; r < N; r++) {
                    for (int s = r + 1; s < N; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                            number++;
                        }
                    }
                }
            }
        }
        return number;
    }

    public Point Maxycoord(Point p, Point q, Point r, Point s) {
        Point p1 = p;
        if (q.compareTo(p1) == 1) p1 = q;
        if (r.compareTo(p1) == 1) p1 = r;
        if (s.compareTo(p1) == 1) p1 = s;
        return p1;
    }

    public Point Minycoord(Point p, Point q, Point r, Point s) {
        Point p1 = p;
        if (q.compareTo(p1) == -1) p1 = q;
        if (r.compareTo(p1) == -1) p1 = r;
        if (s.compareTo(p1) == -1) p1 = s;
        return p1;
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegment = new LineSegment[numberOfSegments()];
        int N = points.length;
        int count=0;
        for (int p = 0; p < N; p++) {
            for (int q = p + 1; q < N; q++) {
                for (int r = q + 1; r < N; r++) {
                    for (int s = r + 1; s < N; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                            lineSegment[count++]=new LineSegment(Minycoord(points[p],points[q],points[r],points[s]),Maxycoord(points[p],points[q],points[r],points[s]));
                        }
                    }
                }
            }
        }
        return lineSegment;
    }
    public static void main(String[] args){
        In in=new In(args[0]);
        int n=in.readInt();
        Point[] points=new Point[n];
        for (int i=0;i<n;i++){
            int x=in.readInt();
            int y=in.readInt();
            points[i]=new Point(x,y);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);
        for (Point p:points){
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints bruteCollinearPoints=new BruteCollinearPoints(points);
        for (LineSegment segment:bruteCollinearPoints.segments()){
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

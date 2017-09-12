package com.algotithm;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;


public class Convexhull {
    private Point[] points;
    private int N;
    private Point[] outPoint;

    public Convexhull(int N, Point[] points) {
        this.N = N;
        this.points = points;
    }

    public int getLeastyPoint() {//返回纵坐标最小的点的索引；
        int minyindex = 0;
        for (int i = 1; i < N; i++) {
            if (points[minyindex].y > points[i].y)
                minyindex = i;
        }
        return minyindex;
    }

    public int AngleTwoPoints(Point point) {//和点P0点的连线与横轴的夹角，即P0Pi向量和（0，1）向量夹角余弦值
        int yleast = getLeastyPoint();
        if (point.x == points[yleast].x && point.y == points[yleast].y)
            return 0;
        else
            return (int) (Math.acos((point.x - points[yleast].x) / (Math.sqrt(Math.pow(point.x - points[yleast].x, 2) + Math.pow(point.y - points[yleast].y, 2)))) * 180);
    }

    class AngleComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {//值大于0，说明o1离P0得角度大,o2就排在前面
            return AngleTwoPoints(o1) - AngleTwoPoints(o2);
        }
    }

    public void sort() {
        Arrays.sort(points, new AngleComparator());
    }

    public Point[] OutPoint() {
        Stack<Point> stack = new Stack<>();
        int size = 0;
        stack.push(points[0]);
        stack.push(points[1]);
        for (int i = 2; i < N; i++) {
            Point a, b;
            Iterator<Point> iterator = stack.iterator();
            b = iterator.next();
            a = iterator.next();//
            if (ccw(a, b, points[i]) > 0)
                stack.push(points[i]);
            else {
                stack.pop();
                i--;
            }
        }
        size = stack.size();
        outPoint = new Point[size];
        for (int j = 0; j < size; j++) {//栈最底部是原点，需除去；
            outPoint[size - 1 - j] = stack.pop();
        }
        return outPoint;
    }

    public static int ccw(Point a, Point b, Point c) {
        double area = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
        if (area < 0) return -1;
        else if (area > 0) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("/Users/guanglinzhou/Desktop/Convexhull.txt");
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int N = StdIn.readInt();
        Point[] points = new Point[N];
        Point[] outPoint;
        int x, y;
        for (int i = 0; i < N; i++) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            points[i] = new Point(x, y);
        }
        Convexhull convexhull = new Convexhull(N, points);
        convexhull.sort();
        outPoint = convexhull.OutPoint();
        print(outPoint);
        System.out.println("--------- ");
    }

    public static void print(Point[] points) {
        for (Point p : points) {
            System.out.print(p.x + "  " + p.y);
            System.out.println();
        }
    }
}
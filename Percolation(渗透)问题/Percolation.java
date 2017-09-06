import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[] a;
    private int N;
    private int numberopen;

    public Percolation(int n) {
        N = n;//N*N gird system
        numberopen = 0;//open sites number
        if (n <= 0)
            throw new IllegalArgumentException("n must be a positive number.");
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        a = new boolean[n * n];
    }

    public boolean isOpen(int row, int col) {
        return a[(row - 1) * N + col - 1] == true;
    }

    public boolean isFull(int row, int col) {//connected to an open site in the top row
        return weightedQuickUnionUF.connected((row - 1) * N + col, 0);
    }

    public int numberOfOpenSites() {
        return numberopen;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, N * N + 1);
    }

    public boolean outOfIndices(int row, int col) {
        if ((row > 0 && row <= N) && (col > 0 && col <= N))
            return false;
        return true;
    }

    public void open(int row, int col) {
        if (outOfIndices(row, col))
            throw new IllegalArgumentException("outOfIndices......");
        if (isOpen(row, col))
            return;
        if (row == 1) {//top sites
            weightedQuickUnionUF.union(0, (row - 1) * N + col);
            a[(row - 1) * N + col - 1] = true;
        } else if (row == N) {
            weightedQuickUnionUF.union(N * N + 1, (row - 1) * N + col);
            a[(row - 1) * N + col - 1] = true;
        }
        if (!outOfIndices(row - 1, col) && isOpen(row - 1, col)) {//上site
            weightedQuickUnionUF.union((row - 1) * N + col, (row - 2) * N + col);
            a[(row - 1) * N + col - 1] = true;
        }
        if (!outOfIndices(row + 1, col) && isOpen(row + 1, col)) {//下site
            weightedQuickUnionUF.union(row * N + col, (row - 1) * N + col);
            a[(row - 1) * N + col - 1] = true;
        }
        if (!outOfIndices(row, col - 1) && isOpen(row, col - 1)) {//左site
            weightedQuickUnionUF.union((row - 1) * N + col - 1, (row - 1) * N + col);
            a[(row - 1) * N + col - 1] = true;
        }
        if (!outOfIndices(row, col + 1) && isOpen(row, col + 1)) {//右site
            weightedQuickUnionUF.union((row - 1) * N + col, (row - 1) * N + col + 1);
            a[(row - 1) * N + col - 1] = true;
        }
        if (!outOfIndices(row, col + 1)) {
            a[(row - 1) * N + col - 1] = true;
        }
        numberopen++;
    }

    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("/Users/guanglinzhou/Dropbox/Algorithms/Chapter1/Percolation/percolationdata/input50.txt");
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            percolation.open(i, j);
        }
        System.out.println(percolation.percolates());
        System.out.println(percolation.numberOfOpenSites());
    }
}
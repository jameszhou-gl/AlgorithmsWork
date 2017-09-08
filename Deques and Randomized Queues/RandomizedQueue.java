import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] a;

    public RandomizedQueue() {
        N = 0;
        a = (Item[]) new Object[1];
    }

    public Item[] resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
        return a;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (N == a.length)
            a = resize(2 * a.length);
        a[N++] = item;

    }

    public Item dequeue() {
        int i = StdRandom.uniform(N);//return a pseudo-random integer between 0~N-1
        Item item = a[i];
        a[i] = a[N - 1];
        a[N - 1] = item;
        a[--N] = null;//avoid loitering
        if (N <= a.length / 4)
            a = resize(a.length / 2);
        return item;
    }

    public Item sample() {
        int i = StdRandom.uniform(N);//return a pseudo-random integer between 0~N-1
        return a[i];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i != 0;
        }

        public Item next() {
            int j = StdRandom.uniform(i);//return a pseudo-random integer between 0~N-1
            Item item = a[j];
            a[j] = a[i - 1];
            a[i - 1] = item;
            a[--i] = null;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}

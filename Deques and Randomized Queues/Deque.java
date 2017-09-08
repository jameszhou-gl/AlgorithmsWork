import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null...");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            first = last;
            first.next = null;
        } else {
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        N++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null...");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
            last.previous = null;
        } else {
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;

    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.previous;
        N--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return first != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no more items to return");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream("/Users/guanglinzhou/Dropbox/Algorithms/Chapter1/Deques and Randomized Queues/src/tobe.txt");
            System.setIn(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (str.equals("-")) {
                deque.removeFirst();
            } else {
                deque.addLast(str);
            }
        }
        System.out.println(deque.size() + " strings left on queue.");
    }
}

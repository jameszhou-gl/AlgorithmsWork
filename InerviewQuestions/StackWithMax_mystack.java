import java.util.NoSuchElementException;

public class StackWithMax_mystack {
    private int max = Integer.MIN_VALUE;
    private Node first;
    private Node second;
    private int N = 0;
    private class Node {
        int element;
        Node next;
    }

    public void push(int i) {
        Node oldfirst = first;
        first = new Node();
        first.element = i;
        first.next = oldfirst;
        if (first.element > max)
            max = first.element;
        Node oldsecond = second;
        second = new Node();
        second.element = max;
        second.next = oldsecond;
        N++;

    }

    public void pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow");
        first = first.next;
        second = second.next;
        if (second == null)
            max = Integer.MIN_VALUE;
        else max = second.element;
        N--;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int top() {
        return first.element;
    }

    public int getMax() {
        return max;
    }

    public static void main(String[] args) {
    }
}

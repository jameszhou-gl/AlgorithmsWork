public class StackWithMin_mystack {

    /**
     * initialize your data structure here.
     */
    private int min = Integer.MAX_VALUE;
    private Node first;
    private Node second;

    private class Node {
        private int element;
        private Node next;
    }


    public void push(int x) {
        Node oldfirst = first;
        first = new Node();
        first.element = x;
        first.next = oldfirst;
        if (x < min) min = x;
        Node oldsecond = second;
        second = new Node();
        second.element = min;
        second.next = oldsecond;

    }

    public void pop() {
        int ele = first.element;
        first = first.next;
        second = second.next;
        if (second == null) min = Integer.MAX_VALUE;
        else min = second.element;

    }

    public int top() {
        return first.element;
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

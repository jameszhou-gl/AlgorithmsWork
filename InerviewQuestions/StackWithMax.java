import edu.princeton.cs.algs4.Stack;

public class StackWithMax {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int max = Integer.MIN_VALUE;

    public StackWithMax() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void Push(int i) {
        stack1.push(i);
        if (i > max)
            max = i;
        stack2.push(max);
    }

    public void Pop() {
        int i = stack1.pop();
        stack2.pop();
        max = stack2.peek();
    }

    public int getMax() {
        return max;
    }

    public static void main(String[] args) {
        StackWithMax stackWithMax = new StackWithMax();
        stackWithMax.Push(-10);
        stackWithMax.Push(10);
        stackWithMax.Push(5);
        stackWithMax.Push(20);

    }
}

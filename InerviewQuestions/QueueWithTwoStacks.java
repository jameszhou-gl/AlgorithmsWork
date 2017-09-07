import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks {
    private Stack<Integer> stack1;//enqueue
    private Stack<Integer> stack2;//dequeue
    private int N;//队列中元素数
    public QueueWithTwoStacks() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
        N=0;
    }
    public void Enqueue(int i){
        stack1.push(i);
        N++;
    }
    public int Dequeue(){
        int orginStack1Size=stack1.size();
        if (!stack2.isEmpty()) {
            N--;
            return stack2.pop();
        }
        for (int i=0;i<orginStack1Size;i++){
            stack2.push(stack1.pop());
        }
        N--;
        return stack2.pop();
    }
    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public static void main(String[] args){
        QueueWithTwoStacks queueWithTwoStacks=new QueueWithTwoStacks();
        queueWithTwoStacks.Enqueue(1);
        queueWithTwoStacks.Enqueue(2);
        queueWithTwoStacks.Enqueue(3);
        System.out.println(queueWithTwoStacks.Dequeue());
        queueWithTwoStacks.Enqueue(4);
        System.out.println(queueWithTwoStacks.Dequeue());
    }
}

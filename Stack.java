public class Stack<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> top;
    private int size;

    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (top == null) throw new IllegalStateException("Stack is empty");
        T value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public T peek() {
        if (top == null) throw new IllegalStateException("Stack is empty");
        return top.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
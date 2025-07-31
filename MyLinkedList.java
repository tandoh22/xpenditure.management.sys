public class MyLinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> current = head, prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        if (prev == null) head = current.next;
        else prev.next = current.next;
        if (current == tail) tail = prev;
        size--;
        return current.data;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}

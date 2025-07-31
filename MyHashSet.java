public class MyHashSet<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T>[] buckets;
    private int capacity;
    private int size;

    public MyHashSet() {
        capacity = 16;
        buckets = (Node<T>[]) new Node[capacity];
        size = 0;
    }

    private int hash(T key) {
        return (key == null ? 0 : key.hashCode()) & (capacity - 1);
    }

    public boolean add(T key) {
        int index = hash(key);
        Node<T> current = buckets[index];
        while (current != null) {
            if (current.data.equals(key)) return false; // already exists
            current = current.next;
        }
        Node<T> newNode = new Node<>(key);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return true;
    }

    public boolean contains(T key) {
        int index = hash(key);
        Node<T> current = buckets[index];
        while (current != null) {
            if (current.data.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public boolean remove(T key) {
        int index = hash(key);
        Node<T> current = buckets[index], prev = null;
        while (current != null) {
            if (current.data.equals(key)) {
                if (prev == null) buckets[index] = current.next;
                else prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void displayAll() {
        for (Node<T> bucket : buckets) {
            Node<T> current = bucket;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }
    
    public T get(int index) {
    int count = 0;
    for (int i = 0; i < buckets.length; i++) {
        Node<T> current = buckets[i];
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            current = current.next;
            count++;
        }
    }
    throw new IndexOutOfBoundsException("Index out of bounds: " + index);
}

}

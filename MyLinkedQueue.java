public class MyLinkedQueue<T> implements MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    @Override
    public void enqueue(T item) {
        list.add(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        T item = list.get(0);
        list.remove(0);
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}

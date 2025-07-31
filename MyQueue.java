public interface MyQueue<T> {
    void enqueue(T item);      // Add to rear
    T dequeue();               // Remove from front
    T peek();                  // View front item
    boolean isEmpty();         // Check if empty
    int size();                // Number of elements
}

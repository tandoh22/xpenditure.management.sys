public class MinHeap {
    private BankAccount[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new BankAccount[capacity];
        size = 0;
    }

    public void insert(BankAccount acc) {
        heap[size] = acc;
        int current = size;
        size++;

        while (current > 0 && heap[current].balance < heap[(current - 1) / 2].balance) {
            BankAccount temp = heap[current];
            heap[current] = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = temp;
            current = (current - 1) / 2;
        }
    }

    public BankAccount getmin() {
        return heap[0];
    }
}
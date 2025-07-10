public Class MinHeap {
    private BankAccount[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new BankAccount[capacity];
        size = 0;
    }
}
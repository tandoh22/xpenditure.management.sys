public class Receipt {
    public MyQueue<String> receiptQueue = new MyLinkedQueue<>();

    public MyArrayList<String> getReceipt() {
        MyArrayList<String> result = new MyArrayList<>();
        int size = receiptQueue.size();

        if (size == 0) {
            System.out.println("No receipts available.");
            return result;
        }
        for (int i = 0; i < size; i++) {
            String item = receiptQueue.dequeue();
            result.add(item);
            receiptQueue.enqueue(item); 
        }

        return result;
    }

    public void uploadReceipt(String receiptItem) {
        receiptQueue.enqueue(receiptItem);
    }
}

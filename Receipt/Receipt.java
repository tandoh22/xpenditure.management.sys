import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Receipt {
    public Queue<String> receiptQueue = new LinkedList<>();
    public List<String> getReceipt() {
    return new ArrayList<>(receiptQueue);
    }
    public void uploadReceipt(String receiptItem) {
        receiptQueue.add(receiptItem);
    }
}
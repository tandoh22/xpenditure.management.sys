import java.util.LinkedList;
import java.util.Queue;

public class Receipt {
    public Queue<Strings> receiptQueue = new LinkedList<>();
    public void uploadReceipt(String receipt) {
        receiptQueue.add(receipt);
    }
}
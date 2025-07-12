import java.util.LinkedList;
import java.util.Queue;

public class Receipt {
    public Queue<String> receipt = new LinkedList<>();
    public Queue<String> getReceipt() {
        return receipt;
    }
    public void uploadReceipt(String receiptItem) {
        receipt.add(receiptItem);
    }
}
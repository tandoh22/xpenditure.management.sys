import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Receipt {
    public Queue<String> receipt = new LinkedList<>();
    
    public List<String> getReceipt() {
        return new java.util.ArrayList<>(receipt);
    }

    public void uploadReceipt(String receiptItem) {
        receipt.add(receiptItem);
    }
}
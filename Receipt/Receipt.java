import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Receipt {
    private Queue<String> receipt = new LinkedList<>();
    public List<String> getReceipt() {
    return new ArrayList<>(receipt);
    }
    public void uploadReceipt(String receiptItem) {
        receipt.add(receiptItem);
    }
}
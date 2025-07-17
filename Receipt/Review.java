import java.util.Stack;

public class Review {
    public Stack<String> reviewStack = new Stack<>();
    public void reviewReceipt(String receiptQueue) {
        reviewStack.push(receiptQueue);
    }
}
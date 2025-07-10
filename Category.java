import java.util.HashSet;

public class Category {
    private HashSet<String> categories = new HashSet<>();

    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public boolean exists(String category) {
        return categories.contains(category);
    }

    public void displayAll() {
        for (String cat : categories) {
            System.out.println(cat);
        }
    }
}
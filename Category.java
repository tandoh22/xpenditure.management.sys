import java.util.HashSet;
import java.util.List;


public class Category {
    private HashSet<String> categories = new HashSet<>();

    public List<String> getCategories() {
        return new java.util.ArrayList<>(categories);
    }

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
    
    public boolean removeCategory(String category) {
        return categories.remove(category);
    }
}
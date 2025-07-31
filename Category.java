public class Category {
    private MyHashSet<String> categories = new MyHashSet<>();

    public MyArrayList<String> getCategories() {
    MyArrayList<String> list = new MyArrayList<>();
    for (int i = 0; i < categories.size(); i++) {
        list.add(categories.get(i)); // assumes MyHashSet has get(index)
    }
    return list;
}


    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public boolean exists(String category) {
        return categories.contains(category);
    }

    public void displayAll() {
       for (int i = 0; i < categories.size(); i++) {
           String cat = categories.get(i); 
           System.out.println(cat);
        }

    }
    
    public boolean removeCategory(String category) {
        return categories.remove(category);
    }
}
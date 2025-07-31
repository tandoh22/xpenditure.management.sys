public interface MyList<T> {
    void add(T element);
    T get(int index);
    T remove(int index);
    int size();
}

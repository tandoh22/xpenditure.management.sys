import java.util.Iterator;

public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[10]; // initial capacity
        size = 0;
    }

    @Override
    public void add(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            return get(index++);
        }
    };
}

}

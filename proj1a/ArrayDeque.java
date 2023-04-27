public class ArrayDeque<T> {
    private int size;
    private int head;
    private int tail;
    private T[] array;

    public ArrayDeque() {
        size = 0;
        head = 0;
        tail = 0;
        array = (T[]) new Object[8];
    }

    private void resize(int newsize) {
        T[] tmp = (T[]) new Object[newsize];
        if (tail > head) {
            System.arraycopy(array, head, tmp, 0, size);
        } else if (tail == head && size == array.length) {
            System.arraycopy(array, head, tmp, 0, array.length - head);
            System.arraycopy(array, 0, tmp, array.length - head, tail);
        } else {
            System.arraycopy(array, head, tmp, 0, array.length - head);
            System.arraycopy(array, 0, tmp, array.length - head, tail);
        }
        array = tmp;
        head = 0;
        tail = size;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        size += 1;
        head = head - 1 < 0 ? head - 1 + array.length : head - 1;
        array[head] = item;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        size += 1;
        array[tail] = item;
        tail = (tail + 1) % array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int pointer = head;
        for (int i = 0; i < size; i++) {
            System.out.print(array[pointer] + " ");
            pointer = (pointer + 1) % array.length;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < array.length / 2 && size > 8) {
            resize(array.length / 2);
        }
        T tmp = array[head];
        head = (head + 1) % array.length;
        size -= 1;
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < array.length / 2 && size > 8) {
            resize(array.length / 2);
        }
        tail = tail - 1 < 0 ? tail - 1 + array.length : tail - 1;
        size -= 1;
        return array[tail];
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int pointer = head;
        pointer = (pointer + index) % array.length;
        return array[pointer];
    }
}

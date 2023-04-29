public class LinkedListDeque<T> implements Deque<T> {
    private class DLNode {
        T value;
        DLNode rest;
        DLNode last;

        public DLNode(T value, DLNode rest, DLNode last) {
            this.value = value;
            this.rest = rest;
            this.last = last;
        }

        public DLNode(DLNode rest, DLNode last) {
            this.rest = rest;
            this.last = last;
        }

        public DLNode() {
            this.rest = null;
            this.last = null;
        }
    }

    private int size;
    private DLNode sentinalHead;
    private DLNode sentinalEnd;


    public LinkedListDeque() {
        this.size = 0;
        this.sentinalHead = new DLNode(null, null);
        this.sentinalEnd = new DLNode(null, null);
        sentinalHead.rest = sentinalEnd;
        sentinalEnd.last = sentinalHead;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        DLNode tmp = new DLNode(item, sentinalHead.rest, sentinalHead);
        sentinalHead.rest = tmp;
        tmp.rest.last = tmp;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        DLNode tmp = new DLNode(item, sentinalEnd, sentinalEnd.last);
        sentinalEnd.last = tmp;
        tmp.last.rest = tmp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        DLNode pointer = sentinalHead.rest;
        for (int i = 0; i < size; i++) {
            System.out.print(pointer.value + " ");
            pointer = pointer.rest;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        DLNode tmp = sentinalHead.rest;
        sentinalHead.rest = tmp.rest;
        tmp.rest.last = sentinalHead;
        return tmp.value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        DLNode tmp = sentinalEnd.last;
        tmp.last.rest = sentinalEnd;
        sentinalEnd.last = tmp.last;
        return tmp.value;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        DLNode pointer = sentinalHead.rest;
        for (int i = 0; i < index; i++) {
            pointer = pointer.rest;
        }
        return pointer.value;
    }

    private T getrecursive(int index, DLNode head) {
        if (index == 0) {
            return head.value;
        }

        return getrecursive(index - 1, head.rest);
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return getrecursive(index, sentinalHead.rest);
    }
}

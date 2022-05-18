package opgave4;

public class CircularQueue {
    private final String[] queue;
    private int head;
    private int tail;

    public CircularQueue(int MAX_CAPACITY) {
        queue = new String[MAX_CAPACITY];
    }

    public void enqueue(String name) {
        if (isFull()) return;

        queue[++tail - 1] = name;
        tail = tail % queue.length;
    }

    public String dequeue() {
        if (isEmpty()) return null;

        String temp = queue[head];
        queue[++head - 1] = null;
        head = head % queue.length;
        return temp;
    }

    public boolean isFull() {
        return head == tail && queue[head] != null;
    }

    public boolean isEmpty() {
        return head == tail && queue[head] == null;
    }
}

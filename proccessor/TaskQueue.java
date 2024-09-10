import java.util.PriorityQueue;

public class TaskQueue {
    private final PriorityQueue<Task> queue;

    public TaskQueue() {
        this.queue = new PriorityQueue<>(new PriorityComparator());
    }

    public void enqueue(Task task) {
        this.queue.add(task);
    }

    public Task dequeue() {
        return this.queue.poll();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

}

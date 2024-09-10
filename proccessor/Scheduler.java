import java.util.List;

public class Scheduler {
    private final TaskQueue taskQueue;

    public Scheduler(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void scheduleTasks(List<Processor> processors) {
        for (Processor processor : processors) {
            if (processor.isIdle() && !taskQueue.isEmpty()) {
                processor.assignTask(getNextTask());
            }
        }
    }


    private Task getNextTask() {
        return this.taskQueue.dequeue();
    }
}
import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getPriority() != t2.getPriority()) {
            return t2.getPriority() - t1.getPriority();
        } else return t2.getExecutionTime() - t1.getExecutionTime();

    }
}

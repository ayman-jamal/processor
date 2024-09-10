public class Processor {
    private final String id;
    private Task currentTask;
    private boolean idle;

    public Processor(String id) {
        this.id = id;
        this.currentTask = null;
        this.idle = true;
    }

    public void assignTask(Task task) {
        this.currentTask = task;
        this.idle = false;
    }

    public void releaseTask() {
        this.currentTask = null;
        this.idle = true;
    }

    public boolean isIdle() {
        return this.idle;
    }

    public Task getCurrentTask() {
        return this.currentTask;
    }
    public String getId(){
        return this.id;
    }
}

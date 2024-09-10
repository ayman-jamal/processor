public class Task {
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private final String id;
    private int remainingTime;

    public Task(int creationTime, int executionTime, int priority, String id) {
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.id = id;
        this.remainingTime = executionTime;
    }

    public int getCreationTime() {
        return this.creationTime;
    }

    public int getExecutionTime() {
        return this.executionTime;
    }

    public int getPriority() {
        return this.priority;
    }

    public void decrementExecutionTime() {
        this.remainingTime--;
    }
    public int getRemainingTime(){
        return this.remainingTime;
    }

    public String getId(){

        return this.id;
    }
    @Override
    public String toString(){
        return "taskId= "+this.id
                + " creationTime= "+this.creationTime
                + " executionTime= " +this.executionTime
                + " priority= "+this.priority;
    }
}

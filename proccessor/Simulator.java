import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Simulator {
    private final int numProcessors;
    private final int totalClockCycles;
    private final String taskFilePath;
    private final List<Processor> processors;
    private final TaskQueue taskQueue;
    private final Queue<Task> taskQueueTemp;
    private final Scheduler scheduler;
    private final Clock clock;
    private final Logger logger;
    private int numberOfTasks;
    private final Object lock;


    public Simulator(int numProcessors, int totalClockCycles, String taskFilePath) {
        this.numProcessors = numProcessors;
        this.totalClockCycles = totalClockCycles;
        this.numberOfTasks = 0;
        this.taskFilePath = taskFilePath;
        this.processors = new ArrayList<>();
        for (int i = 1; i <= numProcessors; i++) {
            this.processors.add(new Processor("P" + i));
        }
        this.taskQueue = new TaskQueue();
        this.taskQueueTemp = new LinkedList<>();
        this.scheduler = new Scheduler(this.taskQueue);
        this.clock = new Clock();
        this.logger = new Logger();
        lock = new Object();
    }

    public void startSimulation() {
        initializeTasks(this.taskFilePath);
        for (int cycle = 1; cycle <= totalClockCycles; cycle++) {
            for(int i=0;i<=numberOfTasks;i++) {
                Task nextTask = taskQueueTemp.peek();
                if(nextTask==null)
                    continue;
                if (nextTask.getCreationTime() <= cycle) {
                    taskQueue.enqueue(taskQueueTemp.remove());
                }
            }

            updateSimulation(cycle);
            Logger.printLogs();
            clock.tick();

            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public void initializeTasks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int taskId =0;
            while ((line = br.readLine()) != null) {
                String[] taskDetails = line.split(" ");
                if (taskId==0) {
                    numberOfTasks = Integer.parseInt(taskDetails[0].trim());
                    taskId++;
                    continue;
                }
                int creationTime = Integer.parseInt(taskDetails[0].trim());
                int executionTime = Integer.parseInt(taskDetails[1].trim());
                int priority = Integer.parseInt(taskDetails[2].trim());
                String id = "T"+taskId;
                taskId++;
                Task task = new Task(creationTime, executionTime, priority, id);
                taskQueueTemp.add(task);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void updateSimulation(int cycle) {
        scheduler.scheduleTasks(processors);

        for (Processor processor : processors) {
            if (processor.isIdle() && !taskQueue.isEmpty()) {
                Task task = taskQueue.dequeue();
                if (task.getCreationTime()<=cycle)
                    processor.assignTask(task);
                logger.logEvent("TaskAssigned", clock.getCurrentCycle(), "Assigned task " + task.getId() + " to " + processor.getId());
            }
        }

        for (Processor processor : processors) {
            if (!processor.isIdle()) {
                Task task = processor.getCurrentTask();
                task.decrementExecutionTime();
                logger.logEvent("TaskExecution", clock.getCurrentCycle(), "Executing task " + task.getId() + " on " + processor.getId());
                if (task.getRemainingTime() == 0) {
                    processor.releaseTask();
                    logger.logEvent("TaskCompleted", clock.getCurrentCycle(), "Task " + task.getId() + " completed on " + processor.getId());
                }
            }
        }
    }
}

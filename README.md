### **Technical Report for Processor Execution Simulator**

[Watch the video on YouTube](https://youtu.be/SnRED58K6mk?si=dwf_eYn6WlyoJ19Y)
---

### **Introduction**

The **Processor Execution Simulator** models a multi-processor system handling tasks based on their priority and execution time. It focuses on task scheduling, processor assignment, and event logging, simulating task execution across clock cycles and allowing performance insights in a multi-tasking environment.

---

### **Class Overview**

1. **Clock**
   - Tracks the current simulation cycle.
   - Provides methods to increment and retrieve the cycle count.

2. **Event**
   - Represents an event (e.g., task execution, task completion) during the simulation.
   - Stores the event type, the cycle it occurs in, and related details.

3. **Logger**
   - Logs events during the simulation.
   - Uses a queue to store events and allows for adding new events and retrieving logs.

4. **PriorityComparator**
   - Compares tasks based on their priority and execution time.
   - Higher priority tasks are preferred, and among tasks with the same priority, shorter execution times take precedence.

5. **Processor**
   - Represents an individual processor.
   - Each processor can either be idle or busy and is responsible for executing tasks assigned to it.

6. **Scheduler**
   - Manages task scheduling and assigns tasks to idle processors.
   - Uses a priority queue to ensure tasks are prioritized correctly.

7. **Simulator**
   - Acts as the core of the simulation.
   - Initializes processors, tasks, and other components, controlling the simulation loop.

8. **Task**
   - Represents a task with specific attributes: creation time, execution time, priority, and identifier.

9. **TaskQueue**
   - Manages tasks using a priority queue to ensure tasks are executed in the correct order based on their priority and execution time.

10. **Main.java**
    - Initializes the Simulator with the number of processors, total clock cycles, and the file path for task inputs.
    - Starts the simulation.

---

### **Simulation Logic Overview**

1. **Initialization**  
   - **Simulator Components:**  
     The Simulator initializes processors, task queues, scheduler, clock, and logger. The number of processors and total clock cycles are specified, and tasks are read from the task file.

   - **Processor Initialization:**  
     Processors are created and added to a list for managing task assignments.

   - **Scheduler and TaskQueue:**  
     The Scheduler is set up to manage tasks from a priority queue, which uses `PriorityComparator` for ordering.

2. **Starting the Simulation**
   - The simulation begins by calling `startSimulation()`, initiating the main loop.

3. **Task Initialization**
   - Tasks are read from a file and added to a temporary task queue, `taskQueueTemp`, to be managed later.

4. **Simulation Loop**
   - The simulation iterates over a predefined number of cycles.
   - During each cycle:
     - Tasks from `taskQueueTemp` are moved to the active `taskQueue` when their creation time is less than or equal to the current cycle.
     - The `updateSimulation()` method manages task scheduling, processor assignments, and task executions.

5. **Task Scheduling**
   - The `scheduleTasks()` method assigns tasks to idle processors based on their priority.  
   - Tasks are dequeued from the task queue and assigned to processors, and corresponding events are logged.

6. **Task Execution**
   - Processors execute their assigned tasks, decrementing their execution time.
   - Once a task completes, it is removed from the processor, and events are logged.

7. **Logging**
   - All relevant events (e.g., task execution, completion) are logged and printed after each simulation cycle.

---

### **Data Structures Used**

1. **Queue (LinkedList):**  
   Utilized by the Logger to store events in a FIFO order for easy retrieval and logging.
   
2. **PriorityQueue:**  
   Used in the TaskQueue to maintain an ordered list of tasks based on their priority and execution time.
   
3. **ArrayList:**  
   Used in the Simulator to store processors, allowing for fast access when iterating through available processors.

---

### **Coupling and Cohesion**

- **Low Coupling:**  
  Each class handles a distinct function, minimizing interdependencies. For instance, the Clock handles only the simulation's timing, while the Logger deals solely with event logging. Interactions between classes occur via well-defined interfaces, reducing tight coupling.
  
- **High Cohesion:**  
  Each class has a single, well-defined responsibility. For example, the Processor class manages task assignments and execution, while the Scheduler focuses only on task scheduling.



### **Conclusion**

The Processor Execution Simulator provides a comprehensive view of task scheduling and execution in a multi-processor environment. It utilizes priority-based scheduling, efficient processor management, and event-driven logging to offer insights into multi-tasking system behavior.

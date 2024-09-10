import java.util.LinkedList;
import java.util.Queue;

public class Logger {
    private static Queue<Event> events;

    public Logger() {
        this.events = new LinkedList<>();
    }

    public void logEvent(String type, int cycle, String details) {
        Event event = new Event(type, cycle, details);
        this.events.add(event);
    }

    public static void printLogs() {

        while(!events.isEmpty()){
            System.out.println(events.remove().toString());
        }
    }

    @Override
    public String toString() {
        return "Logger{" +
                "events=" + events +
                '}';
    }
}

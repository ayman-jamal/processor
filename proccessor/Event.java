public class Event {
    private String type;
    private int cycle;
    private String details;

    public Event(String type, int cycle, String details) {
        this.type = type;
        this.cycle = cycle;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public int getCycle() {
        return cycle;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", cycle=" + cycle +
                ", details='" + details + '\'' +
                '}';
    }
}

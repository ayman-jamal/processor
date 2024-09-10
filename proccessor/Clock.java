public class Clock {
    private int currentCycle;

    public Clock() {
        this.currentCycle = 1;
    }

    public void tick() {
        this.currentCycle++;
    }

    public int getCurrentCycle() {
        return this.currentCycle;
    }
}

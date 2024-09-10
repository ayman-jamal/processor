public class Main {
    public static void main(String[] args) {
        int numProcessor = Integer.parseInt(args[0]);
        int totalClockCycle = Integer.parseInt(args[1]);
        Simulator simulator = new Simulator(numProcessor, totalClockCycle, args[2]);

        simulator.startSimulation();
    }
}

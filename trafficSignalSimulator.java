public class TrafficSignalSimulator {

    public static void main(String[] args) {

        while (true) {

            // RED Signal
            System.out.println("🔴 RED - STOP");
            waitFor(5);

            // GREEN Signal
            System.out.println("🟢 GREEN - GO");
            waitFor(5);

            // YELLOW Signal
            System.out.println("🟡 YELLOW - WAIT");
            waitFor(2);
        }
    }

    // Method to wait for given seconds
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted.");
        }
    }
}

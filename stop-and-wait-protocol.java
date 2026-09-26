import java.util.Scanner;

public class StopAndWait {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();

        System.out.print("Enter frame number to lose: ");
        int lost = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nSending Frame " + i);

            if (i == lost) {
                System.out.println("Frame " + i + " lost!");
                System.out.println("Timeout occurred.");
                System.out.println("Retransmitting Frame " + i);
                System.out.println("Frame " + i + " received.");
                System.out.println("ACK " + i + " received.");
                lost = -1;
            } else {
                System.out.println("Frame " + i + " received.");
                System.out.println("ACK " + i + " received.");
            }
        }

        System.out.println("\nAll frames transmitted successfully.");

        sc.close();
    }
}

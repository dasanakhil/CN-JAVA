import java.util.Scanner;

public class NetworkSimulation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Network Simulation =====");

        System.out.print("Enter total number of packets: ");
        int totalPackets = sc.nextInt();

        System.out.print("Enter TCP packets: ");
        int tcpPackets = sc.nextInt();

        System.out.print("Enter UDP packets: ");
        int udpPackets = sc.nextInt();

        System.out.print("Enter TCP packets dropped: ");
        int tcpDropped = sc.nextInt();

        System.out.print("Enter UDP packets dropped: ");
        int udpDropped = sc.nextInt();

        System.out.print("Enter data rate (Mbps): ");
        double dataRate = sc.nextDouble();

        System.out.print("Enter simulation time (seconds): ");
        double time = sc.nextDouble();

        // Total dropped packets
        int totalDropped = tcpDropped + udpDropped;

        // Packets successfully received
        int receivedPackets = totalPackets - totalDropped;

        // Assume each packet is 1000 bytes
        int packetSize = 1000;

        // Calculate throughput
        double throughput =
                (receivedPackets * packetSize * 8)
                / time / 1000000;

        System.out.println("\n===== RESULTS =====");

        System.out.println("Total Packets       : " + totalPackets);
        System.out.println("TCP Packets         : " + tcpPackets);
        System.out.println("UDP Packets         : " + udpPackets);

        System.out.println("TCP Packets Dropped : " + tcpDropped);
        System.out.println("UDP Packets Dropped : " + udpDropped);

        System.out.println("Total Packets Dropped : "
                + totalDropped);

        // In this simple simulation,
        // dropped packets are considered congestion drops.
        System.out.println("Congestion Drops    : "
                + totalDropped);

        System.out.println("Data Rate           : "
                + dataRate + " Mbps");

        System.out.println("Received Packets    : "
                + receivedPackets);

        System.out.printf("Throughput          : %.2f Mbps%n",
                throughput);

        System.out.println("\n===== COMPARISON =====");

        System.out.printf("Data Rate  : %.2f Mbps%n",
                dataRate);

        System.out.printf("Throughput : %.2f Mbps%n",
                throughput);

        sc.close();
    }
}

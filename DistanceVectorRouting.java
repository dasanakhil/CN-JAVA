import java.util.Scanner;

public class DistanceVectorRouting {

    static final int INF = 999;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[][] distance = new int[n][n];
        int[][] nextHop = new int[n][n];

        System.out.println("\nEnter the delay/cost between nodes:");
        System.out.println("Enter 999 if there is no direct connection.\n");

        // Read the subnet graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();

                distance[i][j] = cost[i][j];

                if (i == j) {
                    nextHop[i][j] = i;
                } else if (cost[i][j] != INF) {
                    nextHop[i][j] = j;
                } else {
                    nextHop[i][j] = -1;
                }
            }
        }

        // Distance Vector Routing Algorithm
        boolean updated;

        do {
            updated = false;

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    for (int k = 0; k < n; k++) {

                        if (distance[i][k] != INF &&
                            distance[k][j] != INF &&
                            distance[i][j] >
                            distance[i][k] + distance[k][j]) {

                            distance[i][j] =
                                    distance[i][k] + distance[k][j];

                            nextHop[i][j] = nextHop[i][k];

                            updated = true;
                        }
                    }
                }
            }

        } while (updated);

        // Display routing table of every node
        System.out.println("\n========== ROUTING TABLES ==========");

        for (int i = 0; i < n; i++) {

            System.out.println("\nRouting Table for Node " + i);
            System.out.println("--------------------------------");
            System.out.println("Destination\tDelay\tNext Hop");

            for (int j = 0; j < n; j++) {

                System.out.print(j + "\t\t");

                if (distance[i][j] == INF) {
                    System.out.println("INF\t-");
                } else {
                    System.out.println(
                            distance[i][j] + "\t" +
                            nextHop[i][j]
                    );
                }
            }
        }

        sc.close();
    }
}

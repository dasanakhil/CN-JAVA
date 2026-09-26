import java.util.*;

public class BroadcastTree {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take number of hosts in the subnet
        System.out.print("Enter number of hosts: ");
        int n = sc.nextInt();

        // Adjacency list to represent the network
        ArrayList<Integer>[] network = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            network[i] = new ArrayList<>();
        }

        // Take number of connections between hosts
        System.out.print("Enter number of connections: ");
        int edges = sc.nextInt();

        System.out.println("Enter connections (host1 host2):");

        // Read connections
        for (int i = 0; i < edges; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            // Since network connections are bidirectional
            network[u].add(v);
            network[v].add(u);
        }

        // Take the source host
        System.out.print("Enter source host: ");
        int source = sc.nextInt();

        // Array to check whether a host is already visited
        boolean[] visited = new boolean[n];

        // Queue is used for Breadth First Search (BFS)
        Queue<Integer> queue = new LinkedList<>();

        // Start broadcasting from source host
        queue.add(source);
        visited[source] = true;

        System.out.println("\nBroadcast Tree:");
        System.out.println("Source Host: " + source);

        // Perform BFS
        while (!queue.isEmpty()) {

            // Remove current host from queue
            int current = queue.poll();

            // Visit all connected hosts
            for (int next : network[current]) {

                // If host has not received the broadcast
                if (!visited[next]) {

                    visited[next] = true;

                    // Add host to queue
                    queue.add(next);

                    // This connection becomes part of broadcast tree
                    System.out.println(
                        "Host " + current + " -> Host " + next
                    );
                }
            }
        }

        sc.close();
    }
}

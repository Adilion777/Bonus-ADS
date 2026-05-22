import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        validateVertex(src);
        validateVertex(dest);
        adjacencyList.get(src).add(new Edge(dest, weight));
    }

    public void addUndirectedEdge(int src, int dest, int weight) {
        addEdge(src, dest, weight);
        addEdge(dest, src, weight);
    }
    public void dijkstra(int start) {
        validateVertex(start);

        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] prev = new int[vertices]; // for path reconstruction

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int iteration = 0; iteration < vertices; iteration++) {
            // Step a: find the unvisited vertex with the minimum distance
            int u = pickMinDistance(dist, visited);
            if (u == -1) break; // remaining vertices are unreachable

            visited[u] = true;

            // Step c: relax edges from u
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.getDestination();
                int newDist = dist[u] + edge.getWeight();

                if (!visited[v] && dist[u] != Integer.MAX_VALUE && newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                }
            }
        }

        printResults(start, dist, prev);
    }

    private int pickMinDistance(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private String reconstructPath(int start, int dest, int[] prev) {
        if (prev[dest] == -1 && dest != start) {
            return "no path";
        }

        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = prev[at]) {
            path.add(0, at);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(path.get(i));
        }
        return sb.toString();
    }

    private void printResults(int start, int[] dist, int[] prev) {
        System.out.println("\n=== Dijkstra's Shortest Path from vertex " + start + " ===");
        System.out.printf("%-10s %-12s %s%n", "Vertex", "Distance", "Path");
        System.out.println("-".repeat(50));

        for (int i = 0; i < vertices; i++) {
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "UNREACHABLE" : String.valueOf(dist[i]);
            String pathStr = (dist[i] == Integer.MAX_VALUE) ? "N/A" : reconstructPath(start, i, prev);
            System.out.printf("%-10d %-12s %s%n", i, distStr, pathStr);
        }
        System.out.println();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("Vertex " + v + " is out of range [0, " + (vertices - 1) + "].");
        }
    }

    public void printGraph() {
        System.out.println("=== Graph Adjacency List ===");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print(edge + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
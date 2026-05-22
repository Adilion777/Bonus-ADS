public class Main {

    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1() {
        System.out.println("########################################");
        System.out.println("  Example 1: Classic 5-Vertex Graph");
        System.out.println("########################################");

        Graph g = new Graph(5);

        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 6);
        g.addEdge(1, 3, 3);
        g.addEdge(1, 4, 1);
        g.addEdge(2, 4, 5);
        g.addEdge(4, 3, 2);
        g.addEdge(4, 2, 4);

        g.printGraph();
        g.dijkstra(0);
    }

    private static void example2() {
        System.out.println("########################################");
        System.out.println("  Example 2: Disconnected Graph");
        System.out.println("########################################");

        Graph g = new Graph(6);

        g.addUndirectedEdge(0, 1, 4);
        g.addUndirectedEdge(0, 2, 1);
        g.addUndirectedEdge(2, 1, 2);
        g.addUndirectedEdge(1, 3, 1);
        g.addUndirectedEdge(2, 3, 5);
        g.addUndirectedEdge(3, 4, 3);

        g.printGraph();
        g.dijkstra(0);
    }
}
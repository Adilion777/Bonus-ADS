
public class Edge {
    private int destination;
    private int weight;

    public Edge(int destination, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative: " + weight);
        }
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "-> " + destination + " (weight: " + weight + ")";
    }
}
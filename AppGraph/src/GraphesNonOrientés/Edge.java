package GraphesNonOrientés;

public class Edge {
    private Node neighbor;

    public Edge (Node neighbor) {
        this.neighbor = neighbor;
    }

    public Node getNeighbor() {
        return neighbor;
    }
}

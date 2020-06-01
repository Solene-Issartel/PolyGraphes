package GraphesNonOrientésQ2;

public class Edge {
    private Node neighbor;

    private int distance;

    public Edge (Node neighbor, int dist) {
        this.neighbor = neighbor;
        this.distance=dist;
    }

    public Node getNeighbor() {
        return neighbor;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

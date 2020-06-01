package GraphesNonOrientésQ2;

import java.util.ArrayList;

public class Node {
    public ArrayList<Edge> neighbors;

    private String label;
    private boolean visited;

    public Node(String label) {
        this.label = label;
        this.neighbors = new ArrayList<Edge>();
        this.visited = false;
    }

    public void removeEdge(Edge edge) {
        neighbors.remove(edge);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}

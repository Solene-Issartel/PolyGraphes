package GraphesNonOrientés;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class GraphNO {
    private ArrayList<Node> nodes;

    public GraphNO() {
        this.nodes = new ArrayList<Node>();
    }

    public Node addNode(String label) {
        Node node =  new Node(label);
        nodes.add(node);
        return node;
    }

    public void addEdge(Node source, Node neighbor) {
        Edge edge = new Edge(neighbor);
        source.neighbors.add(edge);
    }

    public Optional<Edge> findEdgeWithLabel(Node source, Node destination) {
        return source.neighbors.stream()
                .filter (n -> n.getNeighbor().getLabel() == destination.getLabel())
                .findFirst();
    }

    public Optional<Node> findNodeWithLabel(String label) {
        return this.nodes.stream()
                .filter (n -> n.getLabel() == label )
                .findFirst();
    }


    public boolean alreadyIn(Node source,Node destination) {
        //True if the edge already exists, false otherwise
        return findEdgeWithLabel(source, destination) != null || findEdgeWithLabel(destination, source) != null;
    }

    int getIndexNode(String label) {
        Optional<Node> n = this.nodes.stream()
                .filter (nd -> nd.getLabel() == label)
                .findFirst();
        return this.nodes.indexOf(n);
    }

    public ArrayList<Node> breadthFirstSearch(Node source) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(source);

        ArrayList<Node> nodesExplored = new ArrayList<Node>();
        nodesExplored.add(source);
        source.setVisited(true);

        while (queue.size() != 0) {
            Node current = queue.remove();
            for (Edge v : current.neighbors) {
                Node neighborNode = v.getNeighbor();
                if (!neighborNode.isVisited()) {
                    queue.add(neighborNode);
                    neighborNode.setVisited(true);
                    nodesExplored.add(neighborNode);
                }
            }
        }

        return nodesExplored;
    }

    public int getNbConnectedComp() {
        int nb = 0;
        if(this.nodes.isEmpty()){
            return -1;
        } else {
            Node firstNode = this.nodes.get(0);
            ArrayList<Node> res = new ArrayList<Node>();
            for (Node v : this.nodes) {
                if (!v.isVisited()) {
                    res = breadthFirstSearch(v);
                    nb += 1;
                }
            }

            return nb;
        }
    }

}

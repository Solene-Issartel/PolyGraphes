package GraphesNonOrientésQ2;

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

    public void addEdge(Node source, Node neighbor,int distance) {
        Edge edge = new Edge(neighbor,distance);
        source.neighbors.add(edge);
    }

    public int[][] getMatriceAdj() {
        int i = 0;
        int j = 0;
        int n = nodes.size();

        int[][] matrice = new int[n][n]; //matrice carrée

        for (i=0;i<n;i++) {
            Node current = this.nodes.get(i);
            for (Edge v : current.neighbors) {
                j = getIndexNode(v.getNeighbor());
                matrice[i][j]=1;
                matrice[j][i]=1;
            }
        }

        return matrice;
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


    public boolean alreadyIn(Node source, Node destination) {
        //True if the edge already exists, false otherwise
        return findEdgeWithLabel(source, destination) != null || findEdgeWithLabel(destination, source) != null;
    }

    int getIndexNode(Node node) {
        int i=-1;
        for(Node n : this.nodes){
            if(n.equals(node)){
                i= this.nodes.indexOf(n);
            }
        }
        return i;
    }

    //Utilisé avec la matrice d'adjacence -> chaque voisin de chaque noeud
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

    public boolean isNeighbor (Node source, Node destination){
        int[][] matriceAdj = getMatriceAdj();
        int i = getIndexNode(source);
        int j = getIndexNode(destination);
        return matriceAdj[i][j] == 1;
    }

    public ArrayList<Edge> findEdgesFromMatriceAdj(Node source){
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (Node n : this.nodes){
            if(isNeighbor(source,n)){
                Edge e = (Edge) source.neighbors.stream()
                    .filter(node -> node.getNeighbor().equals((n)));
                edges.add(e);
            }
        }
        return edges;
    }

    public ArrayList<Edge> Prim (Node source){
        Queue<Node> queue = new LinkedList<Node>();
        ArrayList<Edge> tree = new ArrayList<Edge>();

        int cost = 0;
        while (queue.size() != 0) {
            Node current = queue.remove();
            ArrayList<Edge> edges = findEdgesFromMatriceAdj(current);

            for(Edge e : edges){
                if(cost >= e.getDistance()){
                    tree.add(e);
                    cost = e.getDistance();
                }
            }
        }

        return tree;
    }
    /*fonction prim(G, s)
    pour tout sommet t
        cout[t] := +∞
        pred[t] := null
    cout[s] := 0
    F := file de priorité contenant les sommets de G avec cout[.] comme priorité
    tant que F ≠ vide
        t := F.defiler
        pour toute arête t--u avec u appartenant à F
            si cout[u] >= poids de l'arête entre les sommets t et u
                pred[u] := t
                cout[u] := poids de l'arête entre les sommets t et u
                F.notifierDiminution(u)

    retourner pred
    */
}

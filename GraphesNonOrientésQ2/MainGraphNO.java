package GraphesNonOrientésQ2;

import java.util.ArrayList;

public class MainGraphNO {
    public static void main(String[] args) {
        GraphNO graph = new GraphNO();

        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Node C = graph.addNode("C");
        Node D = graph.addNode("D");

        graph.addEdge(A,B,2);
        graph.addEdge(B,D,2);
        graph.addEdge(D,C,2);

        int nb = graph.getNbConnectedComp();
        System.out.println("Number of connected component(s) : "+nb);

        boolean isC = true;
        if(nb>1){
            isC = false;
        }
        System.out.println("Is the graph connected ? "+isC);

        int[][] m = graph.getMatriceAdj();

        for (int[] ints : m) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println("");
        }

        ArrayList<Edge> edgesPrim = graph.Prim(A);

        for(Edge e : edgesPrim){
            System.out.println("Edge : " + e.getNeighbor() + " distance : "+e.getDistance());
        }
    }
}

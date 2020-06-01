package GraphesNonOrientés;

public class MainGraphNO {
    public static void main(String[] args) {
        GraphNO graph = new GraphNO();

        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Node C = graph.addNode("C");
        Node D = graph.addNode("D");

        graph.addEdge(A,B);
        graph.addEdge(A,C);

        int nb = graph.getNbConnectedComp();
        System.out.println("Number of connected component(s) : "+nb);

        boolean isC = true;
        if(nb>1){
            isC = false;
        }
        System.out.println("Is the graph connected ? "+isC);

    }
}

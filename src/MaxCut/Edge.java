package MaxCut;

public class Edge {
public int weight;
public Node n1;
public Node n2;

	public Edge(Node n1, Node n2, int weight){
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
	}
}

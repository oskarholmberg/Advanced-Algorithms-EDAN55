package IndependentSet;

import java.util.HashSet;

public class Node {

	public int id;
	public HashSet<Node> neighbours;

	public Node(int id) {
		this.id = id;
	}

	
	public void addNeighbours(HashSet<Node> neighbours) {
		this.neighbours = neighbours;
	}

}

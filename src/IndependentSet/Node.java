package IndependentSet;

import java.util.HashSet;

public class Node {

	public HashSet<Node> neighbours;

	public Node() {
	}

	
	public void addNeighbours(HashSet<Node> neighbours) {
		this.neighbours = neighbours;
	}

}

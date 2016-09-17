package DecrNConq;

import java.util.HashSet;

public class Node {

	private HashSet<Node> neighbours;

	public Node() {

	}

	public void addNeighbours(HashSet<Node> neighbours) {
		this.neighbours = neighbours;
	}

}

package PageRank;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

	static public List<Node> nodes;
	public static int jumps;
	public static double damping;
	
	public static void main(String[] args) {	
		
		damping = 0.85;
		jumps = 100000;
		nodes = setup();
		
		Node currentNode = nodes.get(0);
		
		for (int i = 0; i < jumps; i++) {
			currentNode = jumpNext(currentNode);
		}
		
		nodes.sort((n1, n2) -> Integer.compare(n1.score, n2.score));
		Collections.reverse(nodes);
		
		for(int i = 0; i < Integer.min(5, nodes.size()); i++) {
			System.out.println("Node " + nodes.get(i).id + ": " + nodes.get(i).score + " hits (" + nodes.get(i).score * 100 / jumps + "%)");
		}
		
	}
	
	private static List<Node> setup() {
		return Parser.parse("src/PageRank/Data/three.txt");
	}

	public static Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() > 0 && Math.random() <= damping) {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size()));
			} else {
				nextNode = nodes.get((int) Math.floor(Math.random() * nodes.size()));
			}
			nextNode.increment();
			return nextNode;
		}
	
}

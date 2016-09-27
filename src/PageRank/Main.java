package PageRank;

import java.util.HashSet;
import java.util.List;

public class Main {

	static public List<Node> nodes;
	public double[] scores;
	public static int jumps;
	public static double randomJump;
	
	public static void main(String[] args) {	
		
		randomJump = 0.85;
		jumps = 200;
		nodes = setup();
		
		Node currentNode = nodes.get(0);
		
		for (int i = 0; i < jumps; i++) {
			currentNode = jumpNext(currentNode);
		}
		
		for(int i = 0; i < nodes.size(); i++) {
			System.out.println("Node " + i + ": " + nodes.get(i).score + " hits (" + nodes.get(i).score * 100 / jumps + "%)");
		}
		
	}
	
	private static List<Node> setup() {
		return Parser.parse("src/PageRank/Data/tiny.txt");
	}

	public static Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() > 0 && Math.random() >= randomJump) {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size()));
			} else {
				nextNode = nodes.get((int) Math.floor(Math.random() * nodes.size()));
			}
			nextNode.increment();
			return nextNode;
		}
	
}

package PageRank;

import java.util.HashSet;

public class Main {

	static public Node[] nodes;
	public int[] scores;
	public static int jumps;
	
	public static void main(String[] args) {	
		
		jumps = 100;
		nodes = setup();
		
		Node currentNode = nodes[0];
		
		for (int i = 0; i < jumps; i++) {
			currentNode = jumpNext(currentNode);
		}
		
		
	}
	
	private static Node[] setup() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() == 0) {
				nextNode = nodes[(int) Math.floor(Math.random() * nodes.length)];
				
			} else {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size())).node;
			}
			nextNode.increment();
			return nextNode;
		}
	
}

package PageRank;

import java.util.HashSet;

public class Main {

	static public Node[] nodes;
	public int[] scores;
	public static int jumps;
	public static double randomJump;
	
	public static void main(String[] args) {	
		
		randomJump = 0.85;
		jumps = 100;
		nodes = setup();
		
		Node currentNode = nodes[0];
		
		for (int i = 0; i < jumps; i++) {
			currentNode = jumpNext(currentNode);
		}
		
		for(int i = 0; i < nodes.length; i++) {
			System.out.println("Node " + i + ": " + nodes[i].score);
		}
		
	}
	
	private static Node[] setup() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() > 0 && Math.random() >= randomJump) {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size())).node;
			} else {
				nextNode = nodes[(int) Math.floor(Math.random() * nodes.length)];
			}
			nextNode.increment();
			return nextNode;
		}
	
}

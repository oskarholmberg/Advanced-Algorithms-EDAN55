package PageRank;

import java.util.HashSet;

public class Main {

	public Node[] nodes;
	
	public static void main(String[] args) {	
		
		
		
		
	}
	
	public Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() == 0) {
				nextNode = nodes[(int) Math.floor(Math.random() * nodes.length)];
				
			} else {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size())).node;
			}
			return nextNode;
		}
	
}

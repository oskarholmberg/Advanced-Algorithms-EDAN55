package IndependentSet;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
	
	public static HashSet<Node> start;

	public static void main(String[] args) {
		setup();

	}
	
	public static void setup() {
		start = new HashSet<Node>();
		HashMap<Integer, Node> startMap = new HashMap<Integer, Node>();
		Parser parser = new Parser("filename");
		
		for (int i = 0; i < parser.getSize(); i++) {
			Node n = new Node();
			start.add(n);
			startMap.put(i, n);
		}
		
		for (int i = 0; i < parser.getSize(); i++){
			int[] neighbours = parser.getNeighbours(i);
			HashSet<Node> temp = new HashSet<Node>();
			for (int j = 0; j < neighbours.length; j++) {
				if (neighbours[j] == 1) {
					temp.add(startMap.get(j));
				}
			}
			startMap.get(i).addNeighbours(temp);
		}
	}
}

package IndependentSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main {

	static long dur = 0;

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		System.out.println("best: " + getIndependentSet(setup()));
		System.out.println("time: " + (System.currentTimeMillis() - time));
		// System.out.println(dur);

	}

	public static Collection<Node> setup() {
		HashSet<Node> start = new HashSet<Node>();
		HashMap<Integer, Node> startMap = new HashMap<Integer, Node>();
		Parser parser = new Parser("src/IndependentSet/g80.in");

		for (int i = 0; i < parser.getSize(); i++) {
			Node n = new Node();
			start.add(n);
			startMap.put(i, n);
		}

		for (int i = 0; i < parser.getSize(); i++) {
			int[] neighbours = parser.getNeighbours(i);
			HashSet<Node> temp = new HashSet<Node>();
			for (int j = 0; j < neighbours.length; j++) {
				if (neighbours[j] == 1) {
					temp.add(startMap.get(j));
				}
			}

			startMap.get(i).addNeighbours(temp);
		}
		return start;

	}

	public static HashSet<Node> copySet(Collection<Node> original) {
		HashSet<Node> newNodes = new HashSet<Node>();
		newNodes.addAll(original);
		return newNodes;
	}

	public static int getIndependentSet(Collection<Node> remaining) {

		if (remaining.isEmpty()) {
			return 0;
		}
		// List<Node> empty = new ArrayList<Node>();
		for (Node n : remaining) {
			if (n.neighbours.size() == 0) {
				remaining.remove(n);
				int res = 1 + getIndependentSet(remaining);
				remaining.add(n);
				return res;
			}
			
			if (n.neighbours.size() == 1){
				// R1
				remaining.remove(n);
				Node neighbor = n.neighbours.iterator().next();
				remaining.remove(neighbor);
				for (Node n2 : neighbor.neighbours){
					n2.neighbours.remove(neighbor);
				}
				int res = 1 + getIndependentSet(remaining);
				
				for (Node n2 : neighbor.neighbours){
					n2.neighbours.add(neighbor);
				}
				remaining.add(n);
				remaining.add(neighbor);
				return res;
			}
		}
		// try removing the node
		Node chosen = remaining.iterator().next();
		remaining.remove(chosen);
		for (Node n : chosen.neighbours) {
			n.neighbours.remove(chosen);
		}
		int res1 = getIndependentSet(remaining);
		for (Node n : chosen.neighbours) {
			n.neighbours.add(chosen);
		}
		remaining.add(chosen);

		// try adding the node to independent set

		List<Node> toRemove = new ArrayList<Node>();
		for (Node n : chosen.neighbours) {
			toRemove.add(n);
		}

		for (Node n : toRemove){
			for (Node n2 : n.neighbours){
				n2.neighbours.remove(n);
			}
		}
		remaining.removeAll(toRemove);
		remaining.remove(chosen);
		int res2 = 1 + getIndependentSet(remaining);
		// chosen.neighbours.addAll(toRemove);
		remaining.addAll(toRemove);
		remaining.add(chosen);
		
		for (Node n : toRemove){
			for (Node n2 : n.neighbours){
				n2.neighbours.add(n);
			}
		}
		return res1 > res2 ? res1 : res2;

	}

}

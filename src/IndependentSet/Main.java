package IndependentSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		System.out.println(getIndependentSet(setup(), new HashSet<Node>()));

	}

	public static HashSet<Node> setup() {
		HashSet<Node> start = new HashSet<Node>();
		HashMap<Integer, Node> startMap = new HashMap<Integer, Node>();
		Parser parser = new Parser("src/IndependentSet/g4.in");

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

	public static int getIndependentSet(HashSet<Node> remaining, HashSet<Node> independentSet) {
		if (remaining.isEmpty()) {
			return independentSet.size();
		}

		HashSet<Node> empty = new HashSet<Node>();
		outerLoop: for (Node n : remaining) {
			for (Node n2 : n.neighbours) {
				if (remaining.contains(n2)) {
					continue outerLoop;
				}
			}
			independentSet.add(n);
			empty.add(n);
		}
		for (Node n : empty) {
			remaining.remove(n);
		}

		if (remaining.isEmpty()) {
			return independentSet.size();
		}

		Node chosen = remaining.iterator().next();
		// try removing it, have to create new hashSets
//		HashSet<Node> nodesCopy = copySet(remaining);
//		HashSet<Node> independentSetCopy = copySet(independentSet);
		remaining.remove(chosen);
		int result1 = getIndependentSet(remaining, independentSet);
		remaining.add(chosen);
		

		// try adding the node to independent set, just use the old sets to save
		// performance
		HashSet<Node> toRemove = new HashSet<Node>();
		for (Node n : chosen.neighbours){
			if (remaining.contains(n)){
				toRemove.add(n);
			}
		}
		
		independentSet.add(chosen);
		remaining.removeAll(toRemove);
		int result2 = getIndependentSet(remaining, independentSet);
		independentSet.remove(chosen);
		remaining.addAll(toRemove);
		
		remaining.addAll(empty);
		independentSet.removeAll(empty);

		return (result1 > result2) ? result1 : result2;

	}
}

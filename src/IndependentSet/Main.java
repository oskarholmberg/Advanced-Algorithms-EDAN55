package IndependentSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

	public static HashSet<Node> start;

	public static void main(String[] args) {
		setup();

		System.out.println(getIndependentSet(start, new HashSet<Node>()).size());

	}

	public static void setup() {
		start = new HashSet<Node>();
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

	}

	public static HashSet<Node> copySet(Collection<Node> original) {
		HashSet<Node> newNodes = new HashSet<Node>();
		newNodes.addAll(original);
		return newNodes;
	}

	public static HashSet<Node> getIndependentSet(HashSet<Node> remaining, HashSet<Node> independentSet) {
		if (remaining.isEmpty()) {
			return independentSet;
		}

		Iterator<Node> it = remaining.iterator();
		
		outerLoop:
		while (it.hasNext()){
			Node curr = it.next();
			for (Node n2 : curr.neighbours) {
				if (remaining.contains(n2)) {
					continue outerLoop;
				}
				// no remaining found
				independentSet.add(curr);
				remaining.remove(curr);
			}
		}


		Node chosen = remaining.iterator().next();
		// try removing it, have to create new hashSets
		HashSet<Node> nodesCopy = copySet(remaining);
		HashSet<Node> independentSetCopy = copySet(independentSet);
		nodesCopy.remove(chosen);
		HashSet<Node> result1 = getIndependentSet(nodesCopy, independentSetCopy);

		// try adding the node to independent set, just use the old sets to save
		// performance
		independentSet.add(chosen);
		remaining.removeAll(chosen.neighbours);
		HashSet<Node> result2 = getIndependentSet(remaining, independentSet);

		return (result1.size() > result2.size()) ? result1 : result2;

	}
}

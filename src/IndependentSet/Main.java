package IndependentSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main {

	static long dur = 0;

	public static int best = 0;

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		getIndependentSet(setup(), new HashSet<Node>());
		System.out.println("best: " + best);
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

	public static void getIndependentSet(Collection<Node> remaining, HashSet<Node> independentSet) {
		if (remaining.size() + independentSet.size() <= best) {
			return;
		}

		List<Node> empty = new ArrayList<Node>();
		outerLoop: for (Node n : remaining) {
			for (Node n2 : n.neighbours) {
				if (remaining.contains(n2)) {
					break outerLoop;
				}
			}
			empty.add(n);
		}
		remaining.removeAll(empty);
		independentSet.addAll(empty);

		if (remaining.isEmpty()) {
			best = independentSet.size();
			remaining.addAll(empty);
			independentSet.removeAll(empty);
			return;
		}

		// try removing the node
		Node chosen = remaining.iterator().next();
		remaining.remove(chosen);
		getIndependentSet(remaining, independentSet);
		remaining.add(chosen);

		// try adding the node to independent set
		
		List<Node> toRemove = new ArrayList<Node>();
		for (Node n : chosen.neighbours) {
			if (remaining.contains(n)) {
				toRemove.add(n);
			}
		}

		remaining.removeAll(toRemove);
		independentSet.add(chosen);
		getIndependentSet(remaining, independentSet);
		independentSet.remove(chosen);
		remaining.addAll(toRemove);

		// return to prev
		remaining.addAll(empty);
		independentSet.removeAll(empty);

	}

}

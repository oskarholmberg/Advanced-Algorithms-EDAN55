package IndependentSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		HashSet<Node> allNodes = null;

		System.out.println(getIndependentSet(allNodes, new HashSet<Node>()).size());

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

		outerLoop: for (Node n : remaining) {
			for (Node n2 : n.neighbours) {
				if (remaining.contains(n2)) {
					continue outerLoop;
				}
			}
			// no remaining found
			independentSet.add(n);
			remaining.remove(n);
		}
		HashSet<Node> nodesCopy = copySet(remaining);
		HashSet<Node> independentSetCopy = copySet(independentSet);

		Node chosen = remaining.iterator().next();
		// try removing it, have to create new hashSets
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

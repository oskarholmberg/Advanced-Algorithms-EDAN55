package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithm {

	public static Set<Bag> bags;
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		bags = parser.parse("src/TreeWidth/data/BalancedTree_3_5");

		// precalculate partial solutions
		for (Bag bag : bags) {
			bag.calculateSolutions();
		}

		Set<Node> edgeNodes = new HashSet<Node>();
		for (Bag bag : bags) {
			edgeNodes.addAll(bag.edgeNodes);
		}

		ReturnType finalNodes = getIndependentSet(edgeNodes, new HashSet<Node>(), true);

		System.out.println("value: " + finalNodes.value);
		// normal ind. set algorithm

	}

	public static ReturnType getIndependentSet(Set<Node> remaining, Set<Node> independentSet,
			boolean useAbstractValue) {
		if (remaining.size() == 0) {
			return useAbstractValue ? getValueOf(independentSet) : new ReturnType(independentSet, independentSet.size());
		}
		for (Node n : remaining) {
			if (n.getNeighbours().size() == 0) {
				independentSet.add(n);
				Set<Node> remainingCopy = new HashSet<Node>(remaining);
				remainingCopy.remove(n);
				return getIndependentSet(remainingCopy, independentSet, useAbstractValue);
				// independentSet.remove(n);

			}
		}

		// System.out.println(remaining.size());
		Node mostConn = null;
		int mostConnNbr = -1;
		for (Node n : remaining) {
			if (getValidNeighbors(n.getNeighbours(), remaining) > mostConnNbr) {
				mostConn = n;
				mostConnNbr = n.getNeighbours().size();
			}
		}

		// split

		// try adding to set
		Set<Node> independentSetCP1 = new HashSet<Node>(independentSet);

		independentSetCP1.add(mostConn);
		HashSet<Node> remainingCP1 = new HashSet<Node>(remaining);

		remainingCP1.remove(mostConn);
		remainingCP1.removeAll(mostConn.getNeighbours());

		ReturnType indepRes1 = getIndependentSet(remainingCP1, independentSetCP1, useAbstractValue);

		// try removing
		HashSet<Node> remainingCP2 = new HashSet<Node>(remaining);
		remainingCP2.remove(mostConn);
		ReturnType indepRes2 = getIndependentSet(remainingCP2, independentSetCP1, useAbstractValue);

		int value1 = indepRes1.value;
		int value2 = indepRes2.value;
		return (value1 > value2) ? indepRes1 : indepRes2;

	}

	private static ReturnType getValueOf(Set<Node> independentSet) {
		int tot = 0;
		Set<Node> allNodes = new HashSet<Node>();
		for (Bag b : bags){
			Set<Node> recieved = b.getValueOf(independentSet);
			tot += recieved.size();
			allNodes.addAll(recieved);
		}
		return new ReturnType(allNodes, tot);
	}

	public static int getValidNeighbors(List<Node> neighbors, Set<Node> remaining) {
		int nbr = 0;
		for (Node n : neighbors) {
			if (remaining.contains(n)) {
				nbr++;
			}
		}
		return nbr;

	}

}

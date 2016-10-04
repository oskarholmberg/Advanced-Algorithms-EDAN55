package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithm {

	public static Set<Bag> bags;
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		bags = parser.parse("src/TreeWidth/data/BalancedTree_3_5");

		System.out.println("Bags: " + bags.size());
		// precalculate partial solutions
		for (Bag bag : bags) {
			bag.calculateSolutions();
		}
		System.out.println("done with pre req");

		Set<Node> edgeNodes = new HashSet<Node>();
		for (Bag bag : bags) {
			edgeNodes.addAll(bag.edgeNodes);
		}
		System.out.println("total edge nodes: " + edgeNodes.size());

		ReturnType finalNodes;
		if (bags.size() == 1){
			Set<Node> nodes = bags.iterator().next().nodes;
			finalNodes = getIndependentSet(nodes, new HashSet<Node>(), true);
		} else{
			finalNodes = getIndependentSet(edgeNodes, new HashSet<Node>(), true);

		}
		System.out.println("value: " + finalNodes.value);
		System.out.print("selected:");
		for (Node n : finalNodes.independentSet){
			System.out.print(" " +n.id);
		}
		// normal ind. set algorithm

	}

	public static ReturnType getIndependentSet(Set<Node> remaining, Set<Node> independentSet,
			boolean useAbstractValue) {
		if (remaining.size() == 0) {
			return useAbstractValue ? getValueOf(independentSet) : new ReturnType(independentSet, independentSet.size());
		}
		for (Node n : remaining) {
			if (getValidNeighbors(n.neighbours, remaining) < 2) {
				independentSet.add(n);
				Set<Node> remainingCopy = new HashSet<Node>(remaining);
				remainingCopy.remove(n);
				remainingCopy.removeAll(n.neighbours);
				//System.out.println("leaf");
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
		ReturnType indepRes2 = getIndependentSet(remainingCP2, independentSet, useAbstractValue);

		int value1 = indepRes1.value;
		int value2 = indepRes2.value;
		
		return (value1 > value2) ? indepRes1 : indepRes2;

	}

	private static ReturnType getValueOf(Set<Node> independentSet) {
		Set<Node> allNodes = new HashSet<Node>();
		for (Bag b : bags){
			Set<Node> recieved = b.getValueOf(independentSet);
			allNodes.addAll(recieved);
		}
		return new ReturnType(allNodes, allNodes.size());
	}

	public static int getValidNeighbors(Collection<Node> neighbors, Set<Node> remaining) {
		int nbr = 0;
		for (Node n : neighbors) {
			if (remaining.contains(n)) {
				nbr++;
			}
		}
		return nbr;

	}

}

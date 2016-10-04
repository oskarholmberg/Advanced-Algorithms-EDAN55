package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {
	public List<Node> edgeNodes;
	public int id;
	public Set<Node> nodes;
	// public List<SubTree> neighboringTrees;
	public HashMap<BitSet, Set<Node>> partialSolutions;

	public Bag(int id) {
		this.id = id;
		nodes = new HashSet<Node>();
		edgeNodes = new ArrayList<>();
		partialSolutions = new HashMap<BitSet, Set<Node>>();
	}

	public void calculateSolutions() {
		System.out.println("bag " + id + " edge nodes: " + edgeNodes.size());
		testCombinations(edgeNodes, nodes , new HashSet<Node>());
	}

	public Set<Node> getValueOf(Set<Node> inputNodes) {
		BitSet currSet = new BitSet(edgeNodes.size());
		for (int i = 0; i < edgeNodes.size(); i++) {
			currSet.set(i, inputNodes.contains(edgeNodes.get(i)));
		}
		return partialSolutions.get(currSet);
	}

	public void testCombinations(List<Node> remainingEdgeNodes, Set<Node> remainingNodes, Set<Node> independentSet) {
		if (remainingEdgeNodes.size() == 0) {
			// write partial solution
			//System.out.println("getting indepset");
			ReturnType nodesInIndepSet = Algorithm.getIndependentSet(remainingNodes, independentSet, false);
			BitSet currSet = new BitSet(edgeNodes.size());
			for (int i = 0; i < edgeNodes.size(); i++) {
//				System.out.println("calc");
				currSet.set(i, nodesInIndepSet.independentSet.contains(edgeNodes.get(i)));
			}
			
			System.out.println("bag: " + id + " value of: " + currSet + ": " + nodesInIndepSet.independentSet.size());
			partialSolutions.put(currSet, nodesInIndepSet.independentSet);
//			System.out.println(currSet);
			return;
		}
		//System.out.println("depth: " + remainingEdgeNodes.size());
		ArrayList<Node> remainingEdgeNodesCp1 = new ArrayList<Node>(remainingEdgeNodes);
		ArrayList<Node> remainingEdgeNodesCp2 = new ArrayList<Node>(remainingEdgeNodes);
		Set<Node> independentSetCp1 = new HashSet<Node>(independentSet);
		Set<Node> independentSetCp2 = new HashSet<Node>(independentSet);
		Set<Node> remainingNodesCp1 = new HashSet<Node>(remainingNodes);
		Set<Node> remainingNodesCp2 = new HashSet<Node>(remainingNodes);

		Node current = remainingEdgeNodes.get(0);

		// test include this

		remainingEdgeNodesCp1.remove(current);
		List<Node> toRemove = new ArrayList<Node>();
		toRemove.add(current);
		toRemove.addAll(current.getNeighbours());
		remainingEdgeNodesCp1.removeAll(toRemove);
		remainingNodesCp1.removeAll(toRemove);
		independentSetCp1.add(current);
		testCombinations(remainingEdgeNodesCp1, remainingNodesCp1, independentSetCp1);

		// test exclude this

		remainingEdgeNodesCp2.remove(current);
		remainingNodesCp2.remove(current);
		testCombinations(remainingEdgeNodesCp2, remainingNodesCp2, independentSetCp2);

	}

	public void addEdgeNode(Node n) {
		edgeNodes.add(n);
	}
}

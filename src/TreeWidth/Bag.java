package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {
	public List<Node> edgeNodes;
	//public List<SubTree> neighboringTrees;
	public HashMap<BitSet, List<Node>>  partialSolutions;
	
	
	public Bag(){
		partialSolutions = new HashMap<BitSet, List<Node>>();
	}

	public void calculateSolutions(Set<Node> nodes) {
		testCombinations(edgeNodes, nodes, new ArrayList<Node>());
	}
	
	public List<Node> getValueOf(Set<Node> inputNodes){
		BitSet currSet = new BitSet(edgeNodes.size());
		for (int i = 0; i < edgeNodes.size(); i++){
			currSet.set(i, inputNodes.contains(edgeNodes.get(i)));
		}
		
		return partialSolutions.get(currSet);
	}
	
	public void testCombinations(List<Node> remainingEdgeNodes, Set<Node> remainingNodes, List<Node> independentSet){
		if (remainingEdgeNodes.size() == 0){
			// write partial solution
			List<Node> nodesInIndepSet = Algorithm.getIndependentSet(remainingNodes, independentSet);
			BitSet currSet = new BitSet(edgeNodes.size());
			for (int i = 0; i < edgeNodes.size(); i++){
				currSet.set(i, nodesInIndepSet.contains(edgeNodes.get(i)));
			}
		}
		ArrayList<Node> remainingEdgeNodesCp1 = new ArrayList<Node>(remainingEdgeNodes);
		ArrayList<Node> remainingEdgeNodesCp2 = new ArrayList<Node>(remainingEdgeNodes);
		ArrayList<Node> independentSetCp1 = new ArrayList<Node>(independentSet);
		ArrayList<Node> independentSetCp2 = new ArrayList<Node>(independentSet);
		Set<Node> remainingNodesCp1 = new HashSet<Node>(remainingNodes);
		Set<Node> remainingNodesCp2 = new HashSet<Node>(remainingNodes);

		
		Node current = remainingEdgeNodes.get(0);
		remainingEdgeNodesCp1.remove(current);
		remainingEdgeNodesCp2.remove(current);
		
		
		// test include this
		
		
		List<Node> toRemove = new ArrayList<Node>();
		toRemove.add(current);
		toRemove.addAll(current.getNeighbours());
		remainingEdgeNodesCp1.removeAll(toRemove);
		remainingNodes.removeAll(toRemove);
		independentSetCp1.add(current);
		testCombinations(remainingEdgeNodesCp1, remainingNodesCp1, independentSetCp1);
		
		// test exclude this
		
		Set<Node> remainingNodes2 = new HashSet<Node>(remainingNodes);
		remainingNodes2.remove(current);
		testCombinations(remainingEdgeNodesCp2, remainingNodesCp2, independentSetCp2);


	}
	

	public void addEdgeNode(Node n){
		edgeNodes.add(n);
	}
}

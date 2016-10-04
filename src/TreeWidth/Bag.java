package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {
	public Set<Node> nodes;
	public List<Node> edgeNodes;
	//public List<SubTree> neighboringTrees;
	public HashMap<BitSet, List<Node>>  partialSolutions;

	public Bag(){
		nodes = new ArrayList<>();
		edgeNodes = new ArrayList<>();
	}
	
	
	public Bag(){
		
	}

	public void calculatesolutions() {
		testCombinations(edgeNodes, nodes, new ArrayList<Node>());
////		for (Node n : edgeNodes){
//			Set<Node> remainingCopy = new HashSet<Node>();
//			remainingCopy.remove(n);
//			
//			ArrayList<Node> indepSet = new ArrayList<Node>();
//			indepSet.add(n);
//			
//			List<Node> result = Algorithm.getIndependentSet(remainingCopy, new ArrayList<Node>());
//			
//			BitSet currSet = new BitSet(edgeNodes.size());
//			for (int i = 0; i < edgeNodes.size(); i++){
//				currSet.set(i, result.contains(edgeNodes.get(i)));
//			}
//			
//			partialSolutions.put(currSet, result);
			
//		}
		
	}
	
	public void testCombinations(List<Node> remainingEdgeNodes, Set<Node> remainingNodes, List<Node> independentSet){
		if (remainingEdgeNodes.size() == 0){
			
		}
		ArrayList<Node> remainingEdgeNodesCp1 = new ArrayList<Node>(remainingEdgeNodes);
		ArrayList<Node> remainingEdgeNodesCp2 = new ArrayList<Node>(remainingEdgeNodes);

		
		Node current = remainingEdgeNodes.get(0);
		remainingEdgeNodesCp1.remove(current);
		remainingEdgeNodesCp2.remove(current);
		
		ArrayList<Node> independentSetCP1 = new ArrayList<Node>(independentSet);
		ArrayList<Node> independentSetCP2 = new ArrayList<Node>(independentSet);

		
		List<Node> toRemove = new ArrayList<Node>();
		
		// test include this
		
		Set<Node> remainingNodes1 = new HashSet<Node>(remainingNodes);
		toRemove.add(current);
		toRemove.addAll(current.getNeighbours());
		remainingEdgeNodesCp1.removeAll(toRemove);
		remainingNodes.removeAll(toRemove);
//		testCombinations(remainingEdgeNodesCp1, remainingNodes1, )
		
		// test exclude this
		
		Set<Node> remainingNodes2 = new HashSet<Node>(remainingNodes);
		remainingNodes2.remove(current);


	}
	
	

	public void addNode(Node n){
		nodes.add(n);
	}

	public void addEdgeNode(Node n){
		edgeNodes.add(n);
	}
}

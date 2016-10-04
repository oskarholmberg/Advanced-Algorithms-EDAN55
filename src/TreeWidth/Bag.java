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
		
	}

	public void calculatesolutions() {
//		for (Node n : edgeNodes){
			Set<Node> remainingCopy = new HashSet<Node>();
			remainingCopy.remove(n);
			
			ArrayList<Node> indepSet = new ArrayList<Node>();
			indepSet.add(n);
			
			List<Node> result = Algorithm.getIndependentSet(remainingCopy, new ArrayList<Node>());
			
			BitSet currSet = new BitSet(edgeNodes.size());
			for (int i = 0; i < edgeNodes.size(); i++){
				currSet.set(i, result.contains(edgeNodes.get(i)));
			}
			
			partialSolutions.put(currSet, result);
			
//		}
		
	}
	
	public void testCombinations(List<Node> remainingEdgeNodes){
		ArrayList<>
	}
	
	
}

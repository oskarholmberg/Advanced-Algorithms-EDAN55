package TreeWidth;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class Bag {
	public List<Node> nodes;
	public List<Node> edgeNodes;
	//public List<SubTree> neighboringTrees;
	public HashMap<BitSet, List<Node>>  partialSolutions;
	
	public void calculate(HashMap<Integer, BitSet> calculatedSolutions) {
		for (Node n : nodes){
			for (Node n2 : n.getNeighbours()){
				
			}
		}
		
	}
}

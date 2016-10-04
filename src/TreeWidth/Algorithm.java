package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Algorithm {
	


	public static void main(String[] args){
		List<Bag> bags = null;
		List<Node> nodes = null;
		
		// precalculate partial solutions
		for (Bag bag: bags){
			bag.calculatesolutions();
		}
		
		// normal ind. set algorithm
		
//		getIndependentSet(nodes, new ArrayList<Node>());		
		
	}

	public static List<Node> getIndependentSet(Set<Node> remaining, List<Node> independentSet) {
		if (remaining.size() == 0){
			return independentSet;
		}
		for (Node n : remaining){
			if (n.getNeighbours().size() == 0){
				independentSet.add(n);
				Set<Node> remainingCopy = new HashSet<Node>(remaining);
				remainingCopy.remove(n);
				independentSet.add(n);
				return getIndependentSet(remainingCopy, independentSet);
				//independentSet.remove(n);
				
			}
		}
		
		Node mostConn = null;
		int mostConnNbr = 0;
		for (Node n : remaining){
			if (getValidNeighbors(n.getNeighbours(), remaining) > mostConnNbr){
				mostConn = n;
				mostConnNbr = n.getNeighbours().size();
			}
		}
		
		// split
		
		// try adding to set
		
		Node remaining = 0;
		
		
		// try removing
		
		
		return independentSet;
	}
	
	public static int getValidNeighbors(List<Node> neighbors, Set<Node> remaining){
		int nbr = 0;
		for (Node n : neighbors){
			if (remaining.contains(n)){
				nbr++;
			}
		}
		return nbr;
		
	}

}

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
		Set<Node> nodes = null;
		
		// precalculate partial solutions
		for (Bag bag: bags){
			bag.calculateSolutions(nodes);
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
		List<Node> independentSetCP1 = new ArrayList<Node>(independentSet);

		independentSetCP1.add(mostConn);
		HashSet<Node> remainingCP1 = new HashSet<Node>(remaining);
		
		remainingCP1.remove(mostConn);
		remainingCP1.removeAll(mostConn.getNeighbours());
		
		List<Node> indepRes1 = getIndependentSet(remainingCP1, independentSetCP1);
		
		
		
		// try removing	
		HashSet<Node> remainingCP2 = new HashSet<Node>(remaining);
		remainingCP2.remove(mostConn);
		List<Node> indepRes2 = getIndependentSet(remainingCP2, independentSetCP1);
		
		return indepRes1.size() > indepRes2.size() ? indepRes1 : indepRes2;

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

package TreeWidth;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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

		Iterator<Node> it = edgeNodes.iterator();
		Node selected = it.next();
		System.out.println("selected root: " + selected.id);
		

		
		HashSet<Node> indepSetInc = new HashSet<Node>();
		getIndependentSet(selected, edgeNodes, true, indepSetInc);
		
		
		
		// include or exclude
		Set<Node> allNodesInc = new HashSet<Node>();
		for (Bag b : bags){
			allNodesInc.addAll(b.getValueOf(indepSetInc));
		}
		int includeVal = allNodesInc.size();
		
		HashSet<Node> indepSetExc = new HashSet<Node>();
		getIndependentSet(selected, edgeNodes, true, indepSetExc);
		
		Set<Node> allNodesExc = new HashSet<Node>();

		for (Bag b : bags){
			allNodesExc.addAll(b.getValueOf(indepSetExc));
		}
		int excludeVal = allNodesExc.size();
		
		Set<Node> indepSet;
		if (includeVal > excludeVal){
			System.out.println("decided to include node");
			indepSet = allNodesInc;
		} else{
			System.out.println("decided to exlude node");
			indepSet = allNodesExc;
		}

		

		System.out.println("total: " + indepSet.size());
		System.out.print("Selected:");
		for (Node n : indepSet){
			System.out.print(" " + n.id);
		}

	}
	
	public static void getIndependentSet(Node current, Set<Node> remaining, boolean use, Collection<Node> indepSet){
		Set<Node> validNeighbors = getValidNeighborsC(remaining, current.neighbours);
//		Set<Node> left
		HashSet<Node> remainingCP = new HashSet<Node>(remaining);
		remainingCP.remove(current);
		if (use){
//			System.out.println("picked id: " + current.id);
//			for (Node n : validNeighbors){
//				System.out.println("has neighbor: " + n.id);
//			}
			indepSet.add(current);
			remainingCP.removeAll(validNeighbors);
		} 
		if (remainingCP.size() == 0){
			return;
		}
			
		for (Node n : validNeighbors){
			getIndependentSet(n, remainingCP, !use, indepSet);
		}
		

	}

	
	// the normal expensive method
	public static Collection<Node> getIndependentSet(Set<Node> remaining, Set<Node> independentSet) {
		if (remaining.size() == 0) {
			return independentSet;
		}
		for (Node n : remaining) {
			if (getValidNeighbors(n.neighbours, remaining) < 1) {
				independentSet.add(n);
				Set<Node> remainingCopy = new HashSet<Node>(remaining);
				remainingCopy.remove(n);
				remainingCopy.removeAll(n.neighbours);
				//System.out.println("leaf");
				return getIndependentSet(remainingCopy, independentSet);
				// independentSet.remove(n);

			}
		}

		// System.out.println(remaining.size());
		Node mostConn = null;
		int mostConnNbr = -1;
		mostConn = remaining.iterator().next();
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

		Collection<Node> indepRes1 = getIndependentSet(remainingCP1, independentSetCP1);

		// try removing
		HashSet<Node> remainingCP2 = new HashSet<Node>(remaining);
		remainingCP2.remove(mostConn);
		Collection<Node> indepRes2 = getIndependentSet(remainingCP2, independentSet);

		return (indepRes1.size() > indepRes2.size()) ? indepRes1 : indepRes2;

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
	
	public static Set<Node> getValidNeighborsC(Set<Node> neighbors, Set<Node> remaining) {
		Set<Node> nodes = new HashSet<Node>(neighbors);
		nodes.retainAll(remaining);
		return nodes;

	}

}

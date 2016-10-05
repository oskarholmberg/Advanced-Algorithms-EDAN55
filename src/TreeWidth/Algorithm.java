package TreeWidth;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Algorithm {

	public static Set<Bag> bags;
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		bags = new HashSet<>(parser.parse("src/TreeWidth/data/HouseGraph"));
		
		for (Bag b : bags){

		}

		System.out.println("Bags: " + bags.size());
		// precalculate partial solutions
		for (Bag bag : bags) {
			bag.calculateSolutions();
		}
		System.out.println("done with pre calculation");		

		
		HashSet<Node> indepSetInc = new HashSet<Node>();
		
		Bag selectedBag = bags.iterator().next();
		
		
		selectedBag.createTree();
		
		Bag endBag = null;
		for (Bag b : bags){
			if (b.children.size() == 0){
				endBag = b;
			}
			b.printFamily();
		}
		
		
		
		
		
		// include or exclude



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

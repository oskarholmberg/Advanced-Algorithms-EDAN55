package TreeWidth;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Algorithm {

	public static Set<Bag> bags;
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		bags = new HashSet<>(parser.parse("src/TreeWidth/data/HouseGraph"));

		System.out.println("Bags: " + bags.size());
		// precalculate partial solutions
		Collection<Node> allNodes = new HashSet<Node>();
		for (Bag bag : bags) {
			allNodes.addAll(bag.nodes);
		}
		for (Bag bag : bags) {
			bag.calculateSolutions(allNodes);
		}
		System.out.println("done with pre calculation");		

		Bag selectedBag = bags.iterator().next();
		selectedBag.createTree();
		
		for (Bag b : bags){
			b.printFamily();
		}
		
		Collection<Node> sel = selectedBag.getIndependentSet();
		System.out.println();
		
		System.out.print("selected: ");
		for (Node n : sel){
			System.out.print(n.id + " ");
		}
		// include or exclude


	}
	
	public static void getIndependentSet(Node current, Set<Node> remaining, boolean use, Collection<Node> indepSet){
		Set<Node> validNeighbors = getValidNeighborsC(remaining, current.getNeighbours());
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

	
	public static Set<Node> getValidNeighborsC(Set<Node> neighbors, Set<Node> remaining) {
		Set<Node> nodes = new HashSet<Node>(neighbors);
		nodes.retainAll(remaining);
		return nodes;

	}

}

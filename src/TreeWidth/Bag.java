package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {
//	public List<Node> edgeNodes;
	public int id;
	public List<Node> nodes;
	public List<Bag> children;
	public Bag parent;
	public HashMap<BitSet, Set<Node>> partialSolutions;
	
	public Collection<Node> currentIndSet;

	public Bag(int id) {
		this.id = id;
		nodes = new ArrayList<Node>();
		children = new ArrayList<Bag>();
//		edgeNodes = new ArrayList<>();
		partialSolutions = new HashMap<BitSet, Set<Node>>();
	}

	public void calculateSolutions() {
//		System.out.println("bag " + id + " edge nodes: " + edgeNodes.size());
		testCombinations(nodes , new HashSet<Node>());
	}

	public Set<Node> getValueOf(Collection<Node> inputNodes) {
		BitSet currSet = new BitSet(nodes.size());
		for (int i = 0; i < nodes.size(); i++) {
			currSet.set(i, inputNodes.contains(nodes.get(i)));
		}
		return partialSolutions.get(currSet);
	}

	public void testCombinations(Collection<Node> remainingNodes, Set<Node> independentSet) {
		if (remainingNodes.size() == 0) {
			// write partial solution
			
			BitSet currSet = new BitSet(nodes.size());
			for (int i = 0; i < nodes.size(); i++) {
				currSet.set(i, independentSet.contains(nodes.get(i)));
			}
			
//			System.out.println("bag: " + id + " value of: " + currSet + ": " + independentSet.size());
			partialSolutions.put(currSet, independentSet);
			return;
		}
		Set<Node> independentSetCp1 = new HashSet<Node>(independentSet);
		Set<Node> independentSetCp2 = new HashSet<Node>(independentSet);
		Set<Node> remainingNodesCp1 = new HashSet<Node>(remainingNodes);
		Set<Node> remainingNodesCp2 = new HashSet<Node>(remainingNodes);

		Node current = remainingNodes.iterator().next();

		// test include this

		List<Node> toRemove = new ArrayList<Node>();
		toRemove.add(current);
		toRemove.addAll(current.getNeighbours());
		remainingNodesCp1.removeAll(toRemove);
		independentSetCp1.add(current);
		testCombinations(remainingNodesCp1, independentSetCp1);

		// test exclude this

		remainingNodesCp2.remove(current);
		testCombinations(remainingNodesCp2, independentSetCp2);

	}

	
	
	public void addNeighbor(Bag bag) {
		children.add(bag);
		
	}

	public void createTree() {
		for (Bag b : children){
			// make the flow single way
			b.children.remove(this);
			b.parent = this;
			b.createTree();
		}
		
	}
	
	public void printFamily(){
		System.out.print(id + " children: ");
		for (Bag b2 : children){
			System.out.print(b2.id + " ");
		}
		System.out.println(" parent: " + ((parent != null) ? parent.id : "None"));
	}

	public Collection<Node> getIndependentSet() {
		currentIndSet = new HashSet<Node>();
		for (Bag b : children){
			currentIndSet.addAll(b.getIndependentSet());
		}
		List<Collection<Node>> possibilities = new ArrayList<Collection<Node>>(partialSolutions.values());
		
		
		Collections.sort(possibilities, (p1, p2) -> p2.size() - p1.size());
//		System.out.print(id + ": ");
//		for (Collection<Node> c : possibilities){
//			System.out.print(c.size());
//		}
//		System.out.println();
		
		currentIndSet.addAll(possibilities.get(0));
		return currentIndSet;
				
	}
}

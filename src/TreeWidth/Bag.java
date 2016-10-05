package TreeWidth;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {
	// public List<Node> edgeNodes;
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
		// edgeNodes = new ArrayList<>();
		partialSolutions = new HashMap<BitSet, Set<Node>>();
	}

	public void calculateSolutions(Collection<Node> allNodes) {
		// System.out.println("bag " + id + " edge nodes: " + edgeNodes.size());
		testCombinations(nodes, allNodes, new HashSet<Node>());

		System.out.println("b id: " + id);
		for (BitSet b : partialSolutions.keySet()) {
			System.out.println(b);
		}
		for (Node n : nodes) {
			System.out.println(n.id);
		}
	}

	public void testCombinations(Collection<Node> remainingNodes, Collection<Node> allNodes, Set<Node> independentSet) {
		System.out.println(remainingNodes.size());
		if (remainingNodes.size() == 0) {
			// write partial solution

			BitSet currSet = new BitSet(nodes.size());
			// for (int i = 0; i < nodes.size(); i++) {
			// currSet.set(i, independentSet.contains(nodes.get(i)));
			// }
			
			for (Node n : independentSet) {
				currSet.set(nodes.indexOf(n));
			}

			partialSolutions.put(currSet, independentSet);
			// System.out.println("bag: " + id + " value of: " + currSet + ": "
			// + independentSet.size());
			return;
		}

		for (Node current : remainingNodes) {

			Set<Node> independentSetCp1 = new HashSet<Node>(independentSet);
			Set<Node> independentSetCp2 = new HashSet<Node>(independentSet);
			Set<Node> remainingNodesCp1 = new HashSet<Node>(remainingNodes);
			Set<Node> remainingNodesCp2 = new HashSet<Node>(remainingNodes);
			Set<Node> allNodesCp1 = new HashSet<Node>(allNodes);
			Set<Node> allNodesCp2 = new HashSet<Node>(allNodes);
			
			
			// test include this


			
			List<Node> toRemove = new ArrayList<Node>();
			toRemove.add(current);
			toRemove.addAll(current.getNeighbours());
			remainingNodesCp1.removeAll(toRemove);
			allNodesCp1.removeAll(toRemove);
			independentSetCp1.add(current);
			testCombinations(remainingNodesCp1, allNodesCp1, independentSetCp1);

			// test exclude this

			remainingNodesCp2.remove(current);
			allNodesCp2.remove(current);
			testCombinations(remainingNodesCp2, allNodesCp2, independentSetCp2);
		}
		// Node current = remainingNodes.iterator().next();

	}

	public void addNeighbor(Bag bag) {
		children.add(bag);

	}

	public void createTree() {
		for (Bag b : children) {
			// make the flow single way
			b.children.remove(this);
			b.parent = this;
			b.createTree();
		}

	}

	public void printFamily() {
		System.out.print(id + " children: ");
		for (Bag b2 : children) {
			System.out.print(b2.id + " ");
		}
		System.out.println(" parent: " + ((parent != null) ? parent.id : "None"));
	}

	public Collection<Node> getIndependentSet() {
		currentIndSet = new HashSet<Node>();

		Set<Node> intersection = new HashSet<Node>();
		for (Bag b : children) {
			currentIndSet.addAll(b.getIndependentSet());
			intersection.addAll(b.nodes);
		}
		intersection.retainAll(nodes);
		// see that all nodes agree between bags
		Collection<Node> selected = getBestSatisfying(currentIndSet, intersection);
		currentIndSet.addAll(selected);
		return currentIndSet;

	}

	private Collection<Node> getBestSatisfying(Collection<Node> currentIndSet, Collection<Node> overlapping) {

		// req are those that must be a certain way
		BitSet req = new BitSet();

		for (Node n : overlapping) {
			int index = nodes.indexOf(n);
			req.set(index, true);
		}

		// correct is their values
		BitSet correct = new BitSet(nodes.size());

		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			boolean contains = currentIndSet.contains(n);
			correct.set(i, contains);

		}

		Collection<Node> bestFullfilling = null;
		int bestNbr = -1;
		// see which are valid
		for (BitSet b : partialSolutions.keySet()) {
			boolean wasValid = true;
			for (int i = 0; i < correct.length(); i++) {
				if (req.get(i) && !(correct.get(i) == b.get(i))) {
					// invalid
					System.out.println("invalid corr " + correct + " curr  " + b + " req " + req);
					wasValid = false;
					break;
				}
			}
			if (wasValid) {
				// valid, see if it's any good
				System.out.println("valid corr " + correct + " curr  " + b + " req " + req);

				Collection<Node> recieved = partialSolutions.get(b);
				if (recieved.size() > bestNbr) {
					bestNbr = recieved.size();
					bestFullfilling = recieved;
				}
			}

		}
		if (bestFullfilling == null) {
			printFamily();
		}
		return bestFullfilling;
	}
}

package TreeWidth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {

	public int id;
	public List<Node> nodes;
	public List<Bag> children;
	public Bag parent;
	public Set<Set<Node>> IS;

	public Collection<Node> currentIndSet;

	public Bag(int id) {
		this.id = id;
		nodes = new ArrayList<Node>();
		children = new ArrayList<Bag>();
		IS = new HashSet<Set<Node>>();
	}

	public void calculateSolutions() {
		testCombinations(nodes, new HashSet<Node>());

	}

	public void testCombinations(Collection<Node> remainingNodes, Set<Node> independentSet) {
		if (remainingNodes.size() == 0) {
			IS.add(new HashSet<>(independentSet));
			return;
		}

		Node current = remainingNodes.iterator().next();

		// test include this

		Set<Node> toRemove = new HashSet<Node>(current.getNeighbours());
		toRemove.add(current);
		toRemove.retainAll(remainingNodes);
		remainingNodes.removeAll(toRemove);
		independentSet.add(current);
		testCombinations(remainingNodes, independentSet);
		independentSet.remove(current);
		remainingNodes.addAll(toRemove);

		// test exclude this

		remainingNodes.remove(current);
		testCombinations(remainingNodes, independentSet);
		remainingNodes.add(current);
		// }

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

	public void calcIndependentSet() {

		if (children.isEmpty()) {
			return;
		}

		for (Bag b : children) {
			b.calcIndependentSet();
		}

		Set<Set<Node>> newIS = new HashSet<Set<Node>>();
		for (Set<Node> nodesInSet : IS) {
			Set<Node> currentSet = new HashSet<>(nodesInSet);
			for (Bag b : children) {
				currentSet.addAll(b.getValueOf(nodesInSet, nodes));
			}
			newIS.add(currentSet);
		}

		IS = newIS;

	}
	

	public Collection<Node> getBest() {
		Set<Node> bestSet = new HashSet<Node>();
		for (Set<Node> set : IS) {
			if (set.size() > bestSet.size()) {
				bestSet = set;
			}
		}
		return bestSet;
	}

	private Collection<? extends Node> getValueOf(Set<Node> currNodes, Collection<Node> allNodes) {
		Set<Node> overlapping = new HashSet<>(currNodes);
		overlapping.retainAll(nodes);
		Set<Node> overlappingNotUsed = new HashSet<>(allNodes);
		overlappingNotUsed.retainAll(nodes);
		overlappingNotUsed.removeAll(overlapping);
		Set<Node> bestSatisfying = new HashSet<Node>();
		for (Set<Node> set : IS) {
			if (set.containsAll(overlapping) && Collections.disjoint(overlappingNotUsed, set)
					&& set.size() > bestSatisfying.size()) {
				bestSatisfying = set;
			}
		}
		return bestSatisfying;
	}

}

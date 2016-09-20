package IndependentSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main {

	static long dur = 0;

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		System.out.println("best: " + getIndependentSet(setup()));
		System.out.println("time: " + (System.currentTimeMillis() - time));
		// System.out.println(dur);

	}

	public static Collection<Node> setup() {
		HashSet<Node> start = new HashSet<Node>();
		HashMap<Integer, Node> startMap = new HashMap<Integer, Node>();
		Parser parser = new Parser("src/IndependentSet/g80.in");

		for (int i = 0; i < parser.getSize(); i++) {
			Node n = new Node(i);
			start.add(n);
			startMap.put(i, n);
		}

		for (int i = 0; i < parser.getSize(); i++) {
			int[] neighbours = parser.getNeighbours(i);
			HashSet<Node> temp = new HashSet<Node>();
			for (int j = 0; j < neighbours.length; j++) {
				if (neighbours[j] == 1) {
					temp.add(startMap.get(j));
				}
			}

			startMap.get(i).addNeighbours(temp);
		}
		return start;

	}

	public static HashSet<Node> copySet(Collection<Node> original) {
		HashSet<Node> newNodes = new HashSet<Node>();
		newNodes.addAll(original);
		return newNodes;
	}

	public static int getIndependentSet(Collection<Node> remaining) {


		if (remaining.isEmpty()) {
			return 0;
		}
		for (Node n : remaining) {
			if (n.neighbours.size() == 0) {
				remaining.remove(n);
				int res = 1 + getIndependentSet(remaining);
				remaining.add(n);
				return res;
			}

			// R1
			if (n.neighbours.size() == 1) {
				addToIndependent(n, remaining);
				int res = 1 + getIndependentSet(remaining);
				removeFromIndependent(n, remaining);
				return res;
			}

			// R2

			if (n.neighbours.size() == 2) {
				Iterator<Node> it = n.neighbours.iterator();
				Node n1 = it.next();
				Node n2 = it.next();
				if (n1.neighbours.contains(n2)) {
					addToIndependent(n, remaining);
					int res = 1 + getIndependentSet(remaining);
					removeFromIndependent(n, remaining);
					return res;

				}
				else{
					
					// magic
					
					Node newNode = new Node();
					
					HashSet<Node> neighbors = new HashSet<Node>();
					neighbors.addAll(n1.neighbours);
					neighbors.addAll(n2.neighbours);
					newNode.addNeighbours(neighbors);
					
					newNode.neighbours.remove(n1);
					newNode.neighbours.remove(n2);
					newNode.neighbours.remove(n);
					
					for (Node n3 : n1.neighbours){
						n3.neighbours.remove(n1);
					}
					for (Node n3 : n2.neighbours){
						n3.neighbours.remove(n2);
					}
					
					for (Node n3 : newNode.neighbours){
						n3.neighbours.add(newNode);
					}
					
					remaining.remove(n);
					remaining.remove(n1);
					remaining.remove(n2);
					
					remaining.add(newNode);

					int res = 1 + getIndependentSet(remaining);
					
					remaining.remove(newNode);
					remaining.add(n);
					
					remaining.add(n1);
					remaining.add(n2);
					
					for (Node n3 : n1.neighbours){
						n3.neighbours.add(n1);
					}
					for (Node n3 : n2.neighbours){
						n3.neighbours.add(n2);
					}
					
					for (Node n3 : newNode.neighbours){
						n3.neighbours.remove(newNode);
					}

					return res;
				}
			}
		}
		
		// try removing the node
		Node chosen = remaining.iterator().next();
		remaining.remove(chosen);
		for (Node n : chosen.neighbours) {
			n.neighbours.remove(chosen);
		}
		int res1 = getIndependentSet(remaining);
		for (Node n : chosen.neighbours) {
			n.neighbours.add(chosen);
		}
		remaining.add(chosen);

		// try adding the node to independent set

		addToIndependent(chosen, remaining);
		int res2 = 1 + getIndependentSet(remaining);
		removeFromIndependent(chosen, remaining);
		
		// take the best result

		return res1 > res2 ? res1 : res2;

	}

	public static void addToIndependent(Node n, Collection<Node> remaining) {
		remaining.removeAll(n.neighbours);
		remaining.remove(n);
		for (Node n2 : n.neighbours) {
			for (Node n3 : n2.neighbours) {
				if (n3 != n)
					n3.neighbours.remove(n2);
			}
		}
	}

	public static void removeFromIndependent(Node n, Collection<Node> remaining) {
		remaining.addAll(n.neighbours);
		remaining.add(n);
		for (Node n2 : n.neighbours) {
			for (Node n3 : n2.neighbours) {
				n3.neighbours.add(n2);
			}
		}
	}

}

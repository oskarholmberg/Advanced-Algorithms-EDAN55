package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 3;
	public static KnuthArray k;

	public static void main(String[] args) {
		AtomicInteger at = new AtomicInteger(1);

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		Node n = new Node(depth, null, at, nodes);

		k = new KnuthArray(at.get());

		while (!k.isEmpty()) {
			nodes.get(k.popRandom()).mark();
			TreePrinter.printNode(n);
		}
		//TreePrinter.printNode(n);

	}
}

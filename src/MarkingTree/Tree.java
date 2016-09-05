package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 2;
	public static KnuthArray k;

	public static void main(String[] args) {
		AtomicInteger at = new AtomicInteger(0);

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		Node n = new Node(depth, null, at, nodes);

		k = new KnuthArray(at.incrementAndGet());

		while (!k.isEmpty()) {
			nodes.get(k.popRandom()).mark();
			TreePrinter.printNode(n);
		}
		TreePrinter.printNode(n);

	}
}

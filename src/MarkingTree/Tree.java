package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 10;

	public static void main(String[] args) {
		AtomicInteger at = new AtomicInteger(0);
		KnuthArray k = new KnuthArray();

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		Node n = new Node(depth, null, at, nodes, k);

		while (!k.isEmpty()) {
			nodes.get(k.popRandom()).mark();
		}
	}
}

package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 5;
	public static Selector k;

	public static void main(String[] args) {

		AtomicInteger at = new AtomicInteger(0);

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		Node n = new Node(depth-1, null, at, nodes);

		k = new Selector(at.get());

		int count = 0;
		while (!k.isEmpty()) {
			count++;
			int recieved = k.sizePop();
            if(!nodes.get(recieved).marked) nodes.get(recieved).mark(true);
			TreePrinter.printNode(n);
		}
		System.out.println("Covering the tree took: " + count);

	}
}

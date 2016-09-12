package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public enum Mode {
		SIZE, REST, KNUTH

	};

	public static final int depth = 7;
	public static final int runTimes = 1;
	public static final boolean print = true;
	public static final Mode mode = Mode.SIZE;
	public static Selector k;

	public static void main(String[] args) {

		double[] data = new double[runTimes];
		for (int i = 0; i < runTimes; i++) {
			int count = 0;

			AtomicInteger at = new AtomicInteger(0);
			HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
			Node n = new Node(depth - 1, null, at, nodes);
			k = new Selector(at.get());

			while (!k.isEmpty()) {
				count++;
				int recieved = 0	;
				switch (mode) {
				case SIZE:
					recieved = k.sizePop();
					break;
				case REST:
					recieved = k.restPop();
					break;
				case KNUTH:
					recieved = k.knuthPop();
					break;
				}
				if (!nodes.get(recieved).marked)
					nodes.get(recieved).mark(true);
				if (print)
					TreePrinter.printNode(n);
			}
			data[i] = count;
		}
		Statistics s = new Statistics(data);

		System.out.println("Mean: " + s.getMean() + " Variance: " + s.getStdDev() + " Nodes: " + (Math.pow(2, depth)-1));

		// System.out.println("Covering the tree took: " + count);

	}
}

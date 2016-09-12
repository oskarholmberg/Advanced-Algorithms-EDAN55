package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static final int depth = 4;
	public static final int runTimes = 1000000;
	public static final boolean print = false;
	public static Selector k;

	public static void main(String[] args) {


		
		double[] data = new double[runTimes];
		for (int i = 0; i < runTimes; i++){
			int count = 0;
			
			AtomicInteger at = new AtomicInteger(0);
			HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
			Node n = new Node(depth-1, null, at, nodes);
			k = new Selector(at.get());
			
			
			while (!k.isEmpty()) {
				count++;
				int recieved = k.restPop();
	            if(!nodes.get(recieved).marked) nodes.get(recieved).mark(true);
	            if (print)
	            	TreePrinter.printNode(n);
            }
            data[i] = count;
		}
		Statistics s = new Statistics(data);
		
		System.out.println("Mean: " + s.getMean() + " Standard deviation: " + s.getStdDev());
		
//		System.out.println("Covering the tree took: " + count);

	}
}

package MaxCut;

import java.util.Collection;
import java.util.Random;

public class Main {
	public static final int runTimes = 1000;
	public static Random r;

	public static void main(String[] args) {
		int bestResult = Integer.MIN_VALUE;

		Parser parser = new Parser();
		parser.parse("src/MaxCut/pw09_100.9.txt");
		// parser.parse("src/MaxCut/matching_1000.txt");
		r = new Random();

		long time = System.currentTimeMillis();
		Collection<Node> nodes = parser.getNodes().values();
		Collection<Edge> edges = parser.getEdges();
		// int[] histogram = new int[edges.size()];
		// for (int i = 0; i < histogram.length; i++){
		// histogram[i] = 0;
		// }

		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			int currentResult = getValueOfCut(edges);
			// System.out.println(currentResult);
			// histogram[currentResult]++;
			bestResult = Math.max(bestResult, currentResult);
		}
		System.out.println(bestResult);
		// for (int i = 0; i < histogram.length; i++){
		// System.out.println(histogram[i]);
		// }
		System.out.println(bestResult + " runtime: " + (System.currentTimeMillis() - time));

	}

	public static int getValueOfCut(Collection<Edge> edges) {
		int total = 0;
		for (Edge e : edges) {
			if (e.n1.location != e.n2.location)
				total += e.weight;
		}
		return total;
	}

	public static void randomizeNodes(Collection<Node> nodes) {
		for (Node n : nodes) {
			n.location = r.nextInt(2);
		}
	}
}

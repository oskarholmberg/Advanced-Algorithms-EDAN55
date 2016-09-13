package MaxCut;

import java.util.Collection;
import java.util.Random;

public class Main {
	public static final int runTimes = 100;
	public static Random r;

	public static void main(String[] args) {
		int bestResult = Integer.MIN_VALUE;

		Parser parser = new Parser();
		// parser.parse("src/MaxCut/pw09_100.9.txt");
		parser.parse("src/MaxCut/matching_1000.txt");
		r = new Random();

		long time = System.currentTimeMillis();
		Collection<Node> nodes = parser.getNodes().values();
		Collection<Edge> edges = parser.getEdges();

		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			int currentResult = getValueOfCut(edges);
			System.out.println(currentResult);
			bestResult = Math.max(bestResult, currentResult);
		}
		System.out.println(bestResult);

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

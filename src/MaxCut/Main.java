package MaxCut;

import java.util.Collection;
import java.util.Random;

import MarkingTree.Statistics;

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
		double[] data = new double[runTimes];

		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			int currentResult = getValueOfCut(edges);
			System.out.println(currentResult);
			bestResult = Math.max(bestResult, currentResult);
			data[i] = currentResult;
		}
		
		Statistics s = new Statistics(data);

		System.out.println("Mean: " + s.getMean());
		System.out.println("Best result: " + bestResult + " runtime: " + (System.currentTimeMillis() - time));
	}

	public static int getValueOfCut(Collection<Edge> edges) {
		int total = 0;
		for (Edge e : edges) {
			if (e.n1.location != e.n2.location)
				total += e.weight;
		}
		return total;
	}

	public static double getAvgWeight(Collection<Edge> edges){
		double total = 0;
		int nbrEdges = 0;
		for (Edge e : edges) {
			total+=e.weight;
			nbrEdges++;
		}
		System.out.println("nbr edges: " + nbrEdges);
		return total/nbrEdges;
	}

	public static void randomizeNodes(Collection<Node> nodes) {
		for (Node n : nodes) {
			n.location = r.nextInt(2);
		}
	}
}

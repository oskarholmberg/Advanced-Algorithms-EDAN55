package MaxCut;
import java.util.Collection;
import java.util.Random;

public class Main {
	public static final int runTimes = 1000;

	public static void main(String[] args) {
		double bestResult = Double.MIN_VALUE;

		long time = System.currentTimeMillis();

		Parser parser = new Parser();
//		parser.parse("src/MaxCut/matching_1000.txt");
		parser.parse("src/MaxCut/pw09_100.9.txt");

		Collection<Node> nodes = parser.getNodes().values();
		Collection<Edge> edges = parser.getEdges();
		
		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			bestResult = Math.max(bestResult, getValueOfCut(edges));
		}
		System.out.println(bestResult + " runtime: " + (System.currentTimeMillis()-time));

	}

	public static double getValueOfCut(Collection<Edge> edges) {
		double total = 0;
		for (Edge e : edges) {
			if (e.n1.location != e.n2.location)
				total += e.weight;
		}
		return total;
	}

	public static void randomizeNodes(Collection<Node> nodes) {
		Random r = new Random();
		for (Node n : nodes) {
			n.location = r.nextInt(2);
		}
	}
}

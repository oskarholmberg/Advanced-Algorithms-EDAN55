package MaxCut;
import java.util.Collection;
import java.util.Random;

public class Main {
	public static final int runTimes = 100;
	public static Random r;

	public static void main(String[] args) {
		double bestResult = Double.MIN_VALUE;
		double avgResult = 0;
		r = new Random();

		long time = System.currentTimeMillis();

		Parser parser = new Parser();
//		parser.parse("src/MaxCut/matching_1000.txt");
		parser.parse("src/MaxCut/pw09_100.9.txt");

		Collection<Node> nodes = parser.getNodes().values();
		Collection<Edge> edges = parser.getEdges();
		
		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			double cutValue = getValueOfCut(edges);
			bestResult = Math.max(bestResult, cutValue);
			avgResult+= cutValue;
		}
		System.out.println("best result: " + bestResult + " avg cutsize: " + (avgResult/runTimes) +
				" % of OPT: " + (avgResult/runTimes/13568) +
				" runtime: " + (System.currentTimeMillis()-time));
		System.out.println("Avarage weight: " + getAvgWeight(edges));
	}

	public static double getValueOfCut(Collection<Edge> edges) {
		double total = 0;
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

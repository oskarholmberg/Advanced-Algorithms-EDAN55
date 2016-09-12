package MaxCut;
import java.util.Collection;
import java.util.Random;

public class Main {
	public static final int runTimes = 1000;

	public static void main() {
		double bestResult = Double.MIN_VALUE;
		
		Collection<Node> nodes = null;
		Collection<Edge> edges = null;
		
		for (int i = 0; i < runTimes; i++) {
			randomizeNodes(nodes);
			bestResult = Math.max(bestResult, getValueOfCut(edges));
		}

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
			n.location = r.nextInt(1);
		}
	}
}

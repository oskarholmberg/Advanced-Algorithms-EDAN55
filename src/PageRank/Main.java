package PageRank;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

	static public List<Node> nodes;
	public static int jumps;
	public static double damping;
	public static String path;
	
	public static void main(String[] args) {	
		path = "src/PageRank/Data/three.txt";

		damping = 0.85;

		
		jumps = 1;
		nodes = setup();
		
		double[] scoresOld = new double[nodes.size()];
		double[] scoresNew = new double[nodes.size()];
		for (int i = 0; i < nodes.size(); i++) {
			scoresOld[i] = 0.00;
			scoresNew[i] = 0.00;
		}
		
		Node currentNode = nodes.get(0);
		
		while(true){
			currentNode = jumpNext(currentNode);
			jumps++;
			scoresNew[currentNode.id] += 1.00;
			if (!testChange(scoresOld, scoresNew, 0.01)) {
				break;
			}
			scoresOld[currentNode.id] += 1.00;	
		}
		
		nodes.sort((n1, n2) -> Integer.compare(n1.score, n2.score));
		Collections.reverse(nodes);
		
		System.out.println("Jumps: " + jumps);
		
		for(int i = 0; i < Integer.min(5, nodes.size()); i++) {
			System.out.println("Node " + nodes.get(i).id + ": " + nodes.get(i).score + " hits (" + 100 * scoresNew[nodes.get(i).id] / jumps + "%)");
		}
		
	}
	
	private static List<Node> setup() {
		return Parser.parse(path);
	}
	
	
	private static boolean testChange(double[] prev, double[] curr, double d) {
		for (int i = 0; i < prev.length; i++) {
			if (Math.abs(100*prev[i]/(jumps-1) - 100*curr[i]/jumps) > d){
				return true;
			}
		}
		return false;
	}
	


	public static Node jumpNext(Node n) {
		Node nextNode;
		if (n.edges.size() > 0 && Math.random() <= damping) {
				nextNode = n.edges.get((int) Math.floor(Math.random() * n.edges.size()));
			} else {
				nextNode = nodes.get((int) Math.floor(Math.random() * nodes.size()));
			}
			nextNode.increment();
			return nextNode;
		}
	
}

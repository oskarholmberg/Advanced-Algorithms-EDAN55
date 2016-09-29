package PageRank;

import java.util.List;
import java.util.Random;

import org.ejml.simple.SimpleMatrix;

public class LinearAlgebra {

	public static void main(String[] args) {
		String path = "src/PageRank/Data/p2p-Gnutella08-mod.txt";
		
		List<Node> nodes = Parser.parse(path);
		double[] startVector = new double[nodes.size()];
		double[] prevVector;
		startVector[0] = 1;
		
		int count = 0;
		do{
			prevVector = startVector;
			startVector = modifyVector(startVector, nodes, 0.85);
			count++;
		} while (testChange(prevVector, startVector, 0.01));
		
		System.out.println("final vector: ");
		for (int i = 0; i < startVector.length; i++){
			System.out.print(startVector[i] + " ");
		}
		System.out.println();
		System.out.println("count: " + count);


	}

	private static boolean testChange(double[] prev, double[] curr, double d) {
		for (int i = 0; i < prev.length; i++) {
			if (Math.abs((curr[i] - prev[i])/prev[i]) > d){
				return true;
			}
		}

		return false;
	}

	public static void print(SimpleMatrix matrix) {
		for (int i = 0; i < matrix.numRows(); i++) {
			for (int j = 0; j < matrix.numCols(); j++) {
				System.out.print(matrix.get(i, j) + " ");
			}
			System.out.println();
		}
	}


	public static double[] modifyVector(double[] currVector, List<Node> nodes, double dampening) {
		double[] outVector = new double[currVector.length];
		for (int i = 0; i < nodes.size(); i++) {
			double currProb = currVector[i];
			Node currNode = nodes.get(i);
			List<Node> edges = currNode.edges;

			double currDampening = dampening;

			if (edges.size() == 0) {
				currDampening = 0;
			}
			for (Node n : edges) {
				outVector[n.id] += currProb *  currDampening / edges.size();
			}

			for (Node n : nodes) {
				outVector[n.id] += (currProb * (1 - currDampening)) / nodes.size();
			}

		}
		return outVector;

	}

}

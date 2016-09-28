package PageRank;

import java.util.List;
import java.util.Random;

import org.ejml.simple.SimpleMatrix;

public class LinearAlgebra {
	public static final int runs = 100;

	public static int[][] adjecency;
	public int[][] hyperlink;

	public static void main(String[] args) {
		
		

		String path = "src/PageRank/Data/three.txt";
		
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
		
		for (int i = 0; i < startVector.length; i++){
			System.out.print(startVector[i] + " ");
		}
		System.out.println();
		System.out.println("count: " + count);
		
		
		
//		SimpleMatrix s = new SimpleMatrix(LinearAlgebra.getMatrix(path, 0.85));
//		SimpleMatrix original = new SimpleMatrix(LinearAlgebra.getMatrix(path, 0.85));
//		System.out.println("whole matrix");
//		print(s);
//
//		SimpleMatrix startVector = new SimpleMatrix(1, s.numCols());
//		int chosen = 2;
//
//		calcToPower(s, runs);
//		for (int i = 0; i < startVector.numCols(); i++) {
//
//			startVector.set(0, i, (i == chosen) ? 1 : 0);
//		}
//
//		System.out.println();
//		System.out.println("Startvector");
//
//		print(startVector);
//		// SimpleMatrix prev;
//		// int times = 0;
//		// do {
//		// prev = s;
//		// s = s.mult(original);
//		// times++;
//		// } while (testChange(prev, s, 0.01));
//
//		s = startVector.mult(s);
//
//		// print(doubleMatrix);
//
//		// doubleMatrix = Geometry.normalizeRows(doubleMatrix);
//		System.out.println();
//		System.out.println("final");
//		print(s);
//		System.out.println("times to convergence: " + times);

	}

	private static void calcToPower(SimpleMatrix s, int runs2) {
		// TODO Auto-generated method stub

	}

	private static boolean testChange(SimpleMatrix prev, SimpleMatrix s, double d) {
		for (int i = 0; i < prev.numRows(); i++) {
			for (int j = 0; j < prev.numCols(); j++) {
				if (Math.abs(prev.get(i, j) - s.get(i, j)) > d)
					return true;
			}
		}
		return false;
	}
	
	private static boolean testChange(double[] prev, double[] curr, double d) {
		for (int i = 0; i < prev.length; i++) {
			if (Math.abs(prev[i] - curr[i]) > d){
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

	public static double[][] getMatrix(String path, double dampening) {
		List<Node> nodes = Parser.parse(path);

		double[][] outMatrix = new double[nodes.size()][nodes.size()];
		for (int i = 0; i < nodes.size(); i++) {
			Node currNode = nodes.get(i);
			double times[] = new double[nodes.size()];
			for (int k = 0; k < times.length; k++) {
				times[k] = 0;
			}
			double toUseDampening = dampening;
			if (currNode.edges.size() == 0) {
				toUseDampening = 1;
			}

			for (Node n : currNode.edges) {
				times[n.id] += (1d / currNode.edges.size()) * (1 - toUseDampening);
			}

			for (int j = 0; j < nodes.size(); j++) {
				times[j] += toUseDampening / nodes.size();
			}

			for (int j = 0; j < times.length; j++) {
				outMatrix[i][j] = times[j];
			}
			// for (int j = 0; j < nodes.size(); j++){

			// }
		}
		return outMatrix;

	}

	public static double[] modifyVector(double[] currVector, List<Node> nodes, double dampening) {
		double[] outVector = new double[currVector.length];
		for (int i = 0; i < nodes.size(); i++) {
			double currProb = currVector[i];
			Node currNode = nodes.get(i);
			List<Node> edges = currNode.edges;

			double currDampening = dampening;

			if (edges.size() == 0) {
				currDampening = 1;
			}
			for (Node n : edges) {
				outVector[n.id] += currProb * (1 - currDampening) / edges.size();
			}

			for (Node n : nodes) {
				outVector[n.id] += (currProb * currDampening) / nodes.size();
			}

		}
		return outVector;

	}

}

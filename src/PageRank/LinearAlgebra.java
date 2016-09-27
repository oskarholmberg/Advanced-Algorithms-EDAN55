package PageRank;

import java.util.List;
import java.util.Random;

import org.ejml.simple.SimpleMatrix;

public class LinearAlgebra {
	public static final int runs = 100;

	public static int[][] adjecency;
	public int[][] hyperlink;

	public static void main(String[] args) {

		String path = "src/PageRank/Data/medium.txt";
		SimpleMatrix s = new SimpleMatrix(LinearAlgebra.getMatrix(path, 0.85));
		SimpleMatrix original = new SimpleMatrix(LinearAlgebra.getMatrix(path, 0.85));
		System.out.println("whole matrix");
		print(s);

		SimpleMatrix startVector = new SimpleMatrix(1, s.numCols());
		int chosen = 2;

		calcToPower(s, runs);
		for (int i = 0; i < startVector.numCols(); i++) {

			startVector.set(0, i, (i == chosen) ? 1 : 0);
		}

		System.out.println();
		System.out.println("Startvector");
		print(startVector);
		SimpleMatrix prev;
		int times = 0;
		do {
			prev = s;
			s = s.mult(original);
			times++;
		} while (testChange(prev, s, 0.01));

		s = startVector.mult(s);

		// print(doubleMatrix);

		// doubleMatrix = Geometry.normalizeRows(doubleMatrix);
		System.out.println();
		System.out.println("final");
		print(s);
		System.out.println("times to convergence: " + times);

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

}

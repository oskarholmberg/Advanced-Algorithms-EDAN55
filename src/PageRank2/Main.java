package PageRank2;

import org.jblas.DoubleMatrix;
import org.jblas.FloatMatrix;

import PageRank.LinearAlgebra;
import org.jblas.Geometry;

/**
 * Created by erik on 27/09/16.
 */
public class Main {
    public int[][] adjecency;
    public int[][] hyperlink;
    public static void main(String[] args){
        DoubleMatrix floatMatrix = Parser.parse("src/PageRank2/Data/three.txt");
        for (int i = 0; i < floatMatrix.rows; i++){
            for (int j = 0; j < floatMatrix.columns; j++){
                System.out.print(floatMatrix.get(i, j) + " ");
            }
            System.out.println();
        }

        
    }

    public static float[][] getHyperLink(int[][] matrix){
        return null;
    }
}

package PageRank2;

import org.jblas.FloatMatrix;

/**
 * Created by erik on 27/09/16.
 */
public class Main {
    public int[][] adjecency;
    public int[][] hyperlink;
    public static void main(String[] args){

        FloatMatrix floatMatrix = Parser.parse("src/PageRank2/Data/three.txt");

        FloatMatrix hyperlinks = FloatMatrix.ones(floatMatrix.rows, floatMatrix.columns).div(floatMatrix);

        for (int i = 0; i < floatMatrix.rows; i++){
            for (int j = 0; j < floatMatrix.columns; j++){
                
            }
        }


        for (int i = 0; i < floatMatrix.rows; i++){
            for (int j = 0; j < floatMatrix.columns; j++){
                System.out.print(hyperlinks.get(i, j) + " ");
            }
            System.out.println();
        }




    }

    public static float[][] getHyperLink(int[][] matrix){
        return null;
    }
}

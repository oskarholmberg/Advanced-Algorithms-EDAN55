package PageRank2;

import org.jblas.DoubleMatrix;
import org.jblas.FloatMatrix;

/**
 * Created by erik on 27/09/16.
 */
public class Main {
    public int[][] adjecency;
    public int[][] hyperlink;
    public static void main(String[] args){

        int[][] transMatrix = Parser.parse("src/PageRank2/Data/medium.txt");

        for (int i = 0; i < transMatrix[0].length; i++){
            for (int j = 0; j < transMatrix[0].length; j++){
                System.out.print(transMatrix[i][j] + " ");
            }
            System.out.println();
        }


    }
    
}

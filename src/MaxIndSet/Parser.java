package MaxIndSet;

import java.io.*;

/**
 * Created by oskar on 9/20/16.
 */
public class Parser {

    public Parser(){

    }

    public int[][] parse(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int size = Integer.valueOf(br.readLine());
            int col = 0;
            int[][] matrix = new int[size][size];
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < size; j++){
                for(int i = 0; i < line.length; i++){
                    matrix[i][j] = Integer.valueOf(line[i]);
                }
                try{
                    line = br.readLine().split(" ");
                } catch (NullPointerException e){

                }
            }

            return matrix;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package PageRank2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by erik on 27/09/16.
 */
public class Parser {
    public static int[][] parse(String fileName){

        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            int size = Integer.valueOf(line);
            int[][] transMatrix = new int[size][size];

            for (int i = 0; i < size; i++){
                String[] split = br.readLine().split("  ");
                for (int j = 0; j < split.length; j ++){
                    transMatrix[i][Integer.valueOf(split[j].split(" ")[1])] ++;
                }

            }

            return transMatrix;

        } catch (IOException e){

        }
        return null;
    }
}

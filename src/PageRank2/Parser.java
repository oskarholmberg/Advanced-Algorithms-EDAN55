package PageRank2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by erik on 27/09/16.
 */
public class Parser {
    public static int[][] parse(String fileName){

        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            int size = Integer.valueOf(line.trim());
            System.out.println(size);
            int[][] transMatrix = new int[size][size];


            line = br.readLine();
            while (line != null && !line.isEmpty()) {
                boolean readingNode = true;
                int baseNode = 0;
                int edgeNode;
                String[] lineData = line.trim().split(" ");
                for (int i = 0; i < lineData.length; i++) {

                    try {
                        if (readingNode){
                            baseNode = Integer.parseInt(lineData[i]);
                            readingNode = false;
                        }
                        else {
                            edgeNode = Integer.parseInt(lineData[i]);
                            transMatrix[baseNode][edgeNode] ++;
                            readingNode = true;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
				System.out.println(line);
                line = br.readLine();

            }

            return transMatrix;

        } catch (IOException e){
            System.out.println("couldn't find file");
        }
        return null;
    }
}

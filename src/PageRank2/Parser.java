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

            int currNode = 0;
            while (line!=null && !line.isEmpty()){
                boolean readingNode = true;
                String[] lineData = line.trim().split(" ");
                for (int i = 0; i < lineData.length; i++) {
                    int baseNode = 0;
                    int edgeNode;
                    try {
                        if (readingNode){
                            baseNode = Integer.parseInt(lineData[i]);
                            readingNode = false;
                        }
                        else {
                            edgeNode = Integer.parseInt(lineData[i]);
                            nodes.get(baseNode).edges.add(nodes.get(edgeNode));
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

        }
        return null;
    }
}

package PageRank2;

import org.jblas.FloatMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by erik on 27/09/16.
 */
public class Parser {
    public static FloatMatrix parse(String fileName){

        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            int size = Integer.valueOf(line.trim());
            System.out.println(size);

            FloatMatrix floatMatrix = FloatMatrix.zeros(size, size);


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
                            floatMatrix.put(baseNode, edgeNode, floatMatrix.get(baseNode, edgeNode) + 1f);
                            readingNode = true;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
				System.out.println(line);
                line = br.readLine();

            }

            return floatMatrix;

        } catch (IOException e){
            System.out.println("couldn't find file");
        }
        return null;
    }
}

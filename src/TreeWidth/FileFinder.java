package TreeWidth;

import java.io.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class FileFinder {

    private static int maxNodes = 10;

    public static void main(String[] args) {
        BufferedReader br;
        File folder = new File("src/TreeWidth/data");
        for (File f : folder.listFiles()) {
            String name = f.toString();
            if (name.split("\\.")[1].equals("gr")) {
                try {
                    br = new BufferedReader(new FileReader(name));
                    String line = br.readLine();
                    while (!line.split(" ")[0].equals("p")) {
                        line = br.readLine();
                    }

                    int val = Integer.valueOf(line.split(" ")[2]);
                    if (val <= maxNodes) {
                        System.out.println(name + " with " + val + " nodes.");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

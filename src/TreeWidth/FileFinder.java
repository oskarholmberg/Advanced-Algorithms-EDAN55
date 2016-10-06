package TreeWidth;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by oskar on 2016-10-04.
 */
public class FileFinder {

    public FileFinder(){

    }

    public ArrayList<String> findGraphs(String path, int max) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br;
        File folder = new File(path);
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
                    if (val >= max) {
                        list.add(name);
                        System.out.println(name + " with " + val + " nodes.");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}

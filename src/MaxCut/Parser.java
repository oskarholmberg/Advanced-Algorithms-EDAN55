package MaxCut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by oskar on 9/12/16.
 */
public class Parser {

    private HashMap<Integer, Node> nodes;
    private ArrayList<Edge> edges;

    public Parser() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void parse(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            line = br.readLine();
            String[] data;
            while (line != null) {
                data = line.split(" ");
                Node n1;
                Node n2;
                if (nodes.containsKey(Integer.valueOf(data[0]))) {
                    n1 = nodes.get(Integer.valueOf(data[0]));
                }
                else {
                    n1 = new Node(Integer.valueOf(data[0]));
                    nodes.put(Integer.valueOf(data[0]), n1);
                }

                if (nodes.containsKey(Integer.valueOf(data[1]))) {
                    n2 = nodes.get(Integer.valueOf(data[1]));
                }
                else {
                    n2 = new Node(Integer.valueOf(data[1]));
                    nodes.put(Integer.valueOf(data[1]), n2);
                }

                edges.add(new Edge(n1, n2, Integer.valueOf(data[2])));
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}

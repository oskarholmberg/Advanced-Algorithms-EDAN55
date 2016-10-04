package TreeWidth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class Parser {

    public Parser(){

    }

    public List<Node> parseGraph(String path){
        Map<Integer, Node> nodes = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String info = br.readLine();
            int id1;
            int id2;
            Node n1;
            Node n2;
            String edge = br.readLine();
            while(edge != null){
                String[] vertices = edge.split(" ");
                id1 = Integer.valueOf(vertices[0]);
                id2 = Integer.valueOf(vertices[1]);
                n1 = nodes.get(id1);
                n2 = nodes.get(id2);
                if(n1 == null){
                    n1 = new Node(id1);
                    nodes.put(id1, n1);
                }
                if(n2 == null){
                    n2 = new Node(id2);
                    nodes.put(id2, n2);
                }

                n1.addNeighbour(n2);
                n2.addNeighbour(n1);

                edge = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator itr = nodes.keySet().iterator();
        List<Node> list = new ArrayList<>();
        while(itr.hasNext()){
            list.add(nodes.get(itr.next()));
            itr.remove();
        }
        System.out.println(list.size());
        return list;
    }

    public void parseTree(){
    
    }
}

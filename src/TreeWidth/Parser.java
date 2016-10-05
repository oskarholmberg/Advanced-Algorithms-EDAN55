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
    public Collection<Bag> parse(String path){
        Map<Integer, Node> map = parseGraph(path+".gr");
        BufferedReader br;
        Map<Integer, Bag> bags = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(path+".td"));
            String line = br.readLine();
            while(line != null){
                String[] split = line.split(" ");
                switch (split[0]){
                    case "c":
                        break;
                    case "s":
                        break;
                    case "b":
                        Bag bag = new Bag(Integer.valueOf(split[1]));
                        for(int i = 2; i < split.length; i++){
                            Node n = map.get(Integer.valueOf(split[i]));
                            bag.nodes.add(n);
                            n.bags.add(bag);
                        }
                        bags.put(bag.id, bag);
                        break;
                    default:
                    	Bag b1 = bags.get(Integer.valueOf(split[0]));
                    	Bag b2 = bags.get(Integer.valueOf(split[1]));
                    	b1.addNeighbor(b2);
                    	b2.addNeighbor(b1);
                        break;
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Iterator itr = map.keySet().iterator();
//        while(itr.hasNext()){
//            Node n = map.get(itr.next());
//            if(n.bags.size() > 1){
//                for(Bag b : n.bags){
//                    b.edgeNodes.add(n);
//                }
//            }
//        }
        System.out.println(bags.size() + " bags created.");
        return bags.values();
    }

    private Map<Integer, Node> parseGraph(String path){
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
        System.out.println(nodes.size() + " nodes created.");
        return nodes;
    }
}

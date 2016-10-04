package TreeWidth;

import java.util.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class Node {
    private Map<Integer, Node> neighbours;
    private int id;

    public Node(int id){
        this.id = id;
        neighbours = new HashMap<>();
    }

    public void addNeighbour(Node n){
        neighbours.put(n.id, n);
    }

    public List<Node> getNeighbours(){
        List<Node> list = new ArrayList<>();
        Iterator itr = neighbours.keySet().iterator();
        while(itr.hasNext()){
            list.add(neighbours.get(itr.next()));
        }
        return list;
    }
}

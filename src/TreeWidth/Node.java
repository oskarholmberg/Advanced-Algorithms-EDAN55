package TreeWidth;

import java.util.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class Node {
    public Map<Integer, Node> neighbours;
    public int id;
    public Set<Bag> bags;
    private HashMap<BitSet, Integer> partialSolutions; 

    public Node(int id){
        this.id = id;
        neighbours = new HashMap<>();
        bags = new HashSet<>();
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

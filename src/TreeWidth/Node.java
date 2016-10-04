package TreeWidth;

import java.util.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class Node {
    public Set<Node> neighbours;
    public int id;
    public Set<Bag> bags;
    private HashMap<BitSet, Integer> partialSolutions; 

    public Node(int id){
        this.id = id;
        neighbours = new HashSet<Node>();
        bags = new HashSet<Bag>();
    }

    public void addNeighbour(Node n){
//        neighbours.put(n.id, n);
    	neighbours.add(n);
    }

    public Set<Node> getNeighbours(){
//        List<Node> list = new ArrayList<>();
//        Iterator itr = neighbours.keySet().iterator();
//        while(itr.hasNext()){
//            list.add(neighbours.get(itr.next()));
//        }
//        return list;
    	return neighbours;
    }
}

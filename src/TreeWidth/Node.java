package TreeWidth;

import java.util.*;

/**
 * Created by oskar on 2016-10-04.
 */
public class Node {
    private Set<Node> neighbours;
    public int id;
    public Set<Bag> bags;

    public Node(int id){

        this.id = id;
        neighbours = new HashSet<Node>();
        bags = new HashSet<Bag>();
    }

    public void addNeighbour(Node n){
    	neighbours.add(n);
    }

    public Set<Node> getNeighbours(){
    	return neighbours;
    }
}

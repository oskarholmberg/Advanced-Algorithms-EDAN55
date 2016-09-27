package PageRank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {
	
	public int id;
	public int score;
	public List<Node> edges;
	
	public Node(int id){
		score = 0;
		edges = new ArrayList<Node>();
		this.id = id;
	}
	
	
	
	public void increment() {
		score++;
	}
}

package PageRank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {
	
	public int score;
	public List<Node> edges;
	
	public Node(){
		score = 0;
		edges = new ArrayList<Node>();
	}
	
	
	
	public void increment() {
		score++;
	}
}

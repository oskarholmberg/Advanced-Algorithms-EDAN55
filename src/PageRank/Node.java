package PageRank;

import java.util.Collection;
import java.util.List;

public class Node {
	
	public int score;
	public List<Edge> edges;
	
	
	
	
	public void increment() {
		score++;
	}
}

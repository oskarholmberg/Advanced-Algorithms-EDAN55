package marking;

public class Node {
	public Node parent;
	public Node child1;
	public Node child2;
	public boolean marked;
	
	public Node(int remainingDepth, Node parent){
		this.parent = parent;
		System.out.println("created node at level: " + remainingDepth);

		if (remainingDepth > 0){
			child1 = new Node(remainingDepth-1, this);
			child2 = new Node(remainingDepth-1, this);
		}

	}
}

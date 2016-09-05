package MarkingTree;

import java.util.concurrent.atomic.AtomicInteger;

public class Node {
	public Node parent;
	public Node child1;
	public Node child2;
	public boolean marked;
	public int index;
	
	public Node(int remainingDepth, Node parent, AtomicInteger at){
		this.parent = parent;
		index = at.incrementAndGet();
		
		System.out.println("created node at level: " + remainingDepth + " with index: " + index);
		if (remainingDepth > 0){
			child1 = new Node(remainingDepth-1, this, at);
			child2 = new Node(remainingDepth-1, this, at);
		}

	}
}

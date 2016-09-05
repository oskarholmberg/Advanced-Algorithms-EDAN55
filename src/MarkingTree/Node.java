package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
	public Node parent;
	public Node left;
	public Node right;
	public boolean marked;
	public int index;
	
	public KnuthArray k;
	
	public Node(int remainingDepth, Node parent, AtomicInteger at, HashMap<Integer, Node> nodes, KnuthArray k){
		this.parent = parent;
		index = at.incrementAndGet();
		
		this.k = k;
		
		nodes.put(index, this);
		
		System.out.println("created node at level: " + remainingDepth + " with index: " + index);
		if (remainingDepth > 0){
			left = new Node(remainingDepth-1, this, at, nodes, k);
			right = new Node(remainingDepth-1, this, at, nodes, k);
		}

	}

	public String toString(){
        if (marked) {
            return "X";
        }
        else{
            return "O";
        }
    }
	
	// Definitely mark
	public void mark() {
		marked = true;
		if (parent != null)
			parent.potentiallyMark();
		if (left.marked){
			right.marked = true;
		}
		else if (right.marked){
			left.marked = true;
		}
	}

	// Test for marking
	public void potentiallyMark() {
		if (left.marked && right.marked) {
			mark();
		}
	}
}

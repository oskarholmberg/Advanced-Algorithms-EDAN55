package MarkingTree;

import java.util.concurrent.atomic.AtomicInteger;

public class Node {
	public Node parent;
	public Node left;
	public Node right;
	public boolean marked;
	public int index;
	
	public Node(int remainingDepth, Node parent, AtomicInteger at){
		this.parent = parent;
		index = at.incrementAndGet();
		
		System.out.println("created node at level: " + remainingDepth + " with index: " + index);
		if (remainingDepth > 0){
			left = new Node(remainingDepth-1, this, at);
			right = new Node(remainingDepth-1, this, at);
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
	
	public void mark() {
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
			marked = true;
			parent.potentiallyMark();
		}
	}
}

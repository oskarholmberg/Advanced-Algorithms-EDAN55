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
	
	public Node(int remainingDepth, Node parent, AtomicInteger at, HashMap<Integer, Node> nodes){
		this.parent = parent;
		index = at.incrementAndGet();
		marked = false;
				
		nodes.put(index, this);
		
		System.out.println("created node at level: " + remainingDepth + " with index: " + index);
		if (remainingDepth > 0){
			left = new Node(remainingDepth-1, this, at, nodes);
			right = new Node(remainingDepth-1, this, at, nodes);
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
		Tree.k.markNode(index);
		if (parent != null)
			parent.potentiallyMark();
		if (left != null){
			if (left.marked && !right.marked){
				right.mark();
			}
			else if (right.marked && !left.marked){
				left.mark();
			}
		}

	}

	// Test for marking
	public void potentiallyMark() {
		if (left.marked && right.marked && !marked) {
			mark();
		}
	}
}

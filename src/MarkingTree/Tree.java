package MarkingTree;

import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 10;

	public static void main(String[] args){
		AtomicInteger at = new AtomicInteger(0);
		Node n = new Node(depth, null, at);
	}
}

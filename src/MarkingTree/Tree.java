package MarkingTree;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree {
	public static int depth = 3;
	public static KnuthArray k;

	public static void main(String[] args) {
		
		KnuthArray knut = new KnuthArray(15);
		
		for (int i = 0; i < 15; i++) {
			System.out.println(knut.popRandom());
			
		}
		
		

	}
}

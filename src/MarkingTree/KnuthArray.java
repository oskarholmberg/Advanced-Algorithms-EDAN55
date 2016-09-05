package MarkingTree;

import java.util.ArrayList;

public class KnuthArray extends ArrayList<Integer> {

	public int popRandom() {
		int index = (int) Math.ceil(Math.random() * size());
		int chosen = get(index);
		markNode(index);
		return chosen;
	}

	public void markNode(int i) {
		shiftList(indexOf(i));
	}

	private void shiftList(int i) {
		set(i, get(size() - 1));
		remove(size() - 1);
	}
	
	
}

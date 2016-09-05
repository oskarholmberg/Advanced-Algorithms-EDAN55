package MarkingTree;

import java.util.HashMap;

public class KnuthArray {

	private int[] list;
	private int last;
	private HashMap<Integer, Integer> map;

	public KnuthArray(int i) {
		list = new int[i];
		map = new HashMap<Integer, Integer>();
		last = i - 1;
		fillArray(i);
	}

	public int popRandom() {
		return  list[(int) Math.floor(Math.random() * last)];
	}

	public void markNode(int i) {
		shiftList(map.get(i));
	}

	private void shiftList(int i) {
		list[i] = list[last];
		map.put(list[i], i);
		last--;
	}
	
	public boolean isEmpty() {
		if (last == -1) {
			return true;
		} else {
			return false;
		}	
	}

	private void fillArray(int i) {
		for (int k = 1; k <= i; k++) {
			list[k - 1] = k;
			map.put(k, k - 1);
		}
	}
}
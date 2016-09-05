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
		int index = (int) Math.ceil(Math.random() * last);
		int chosen = list[index];
		shiftList(index);
		return chosen;
	}

	public void markNode(int i) {
		shiftList(map.get(i));
	}

	private void shiftList(int i) {
		list[i] = list[last];
		map.put(list[i], i);
		last--;
	}

	private void fillArray(int i) {
		for (int k = 1; k <= i; k++) {
			list[k - 1] = k;
			map.put(k, k - 1);
		}
	}
}
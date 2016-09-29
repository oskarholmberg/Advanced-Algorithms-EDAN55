package PageRank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskar on 2016-09-29.
 */
public class NodeSorter {

    private int[] scores;
    private int[] ids;
    private int length;

    /**
     * Uses quicksort to sort a list of nodes with largest first.
     * @param nodes List to sort
     * @return ArrayList with the five nodes with highest score.
     */
    public List<Node> sort(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) return null;
        length = nodes.size();
        scores = new int[length];
        ids = new int[length];
        for (int i = 0; i < length; i++) {
            scores[i] = nodes.get(i).score;
            ids[i] = nodes.get(i).id;
        }
        quickSort(0, length - 1);
        List<Node> sortedList = new ArrayList<Node>();
        for(int i = length-1; i >= Math.max(0, length-5); i --){
            sortedList.add(nodes.get(ids[i]));
        }
        return sortedList;
    }

    private void quickSort(int low, int high) {
        int i = low;
        int j = high;
        int pivot = scores[low+(high-low)/2];
        while(i <= j){
            while(scores[i] < pivot){
                i++;
            }
            while(scores[j] > pivot){
                j--;
            }
            if(i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
    }

    private void exchangeNumbers(int i, int j){
        int tempScore = scores[i];
        int tempId = ids[i];
        scores[i] = scores[j];
        ids[i] = ids[j];
        scores[j] = tempScore;
        ids[j] = tempId;
    }
}

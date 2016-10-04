package PageRank;

import java.util.Comparator;
import java.util.List;

/**
 * Created by oskar on 2016-09-29.
 */
public class RandomSurfer {

    private static double alpha = 85.0 / 100;
    private static int nbrJumps = 10000;
    private static String path = "src/PageRank/Data/p2p-Gnutella08-mod.txt";

    private static List<Node> nodes = Parser.parse(path);

    public static void main(String[] args) {

        double[] oldScores = new double[nodes.size()];
        double[] newScores = new double[nodes.size()];
        Node curNode = nodes.get(0);

        for (int i = 0; i < nbrJumps; i++) {
            curNode = jump(curNode);
            curNode.increment();
        }

        nodes.sort((n2, n1) -> Integer.compare(n1.score, n2.score));

        for (int i = 0; i < Math.min(5, nodes.size()); i ++) {
            Node n = nodes.get(i);
            System.out.println("Node " + n.id + "\tScore: " + n.score + "\tProbability: " + ((double) n.score / nbrJumps) * 100 + "%");
        }
    }

    /**
     * Jump to a new node.
     *
     * @param node , Starting node
     * @return , End node
     */
    public static Node jump(Node node) {
        if (Math.random() > alpha || node.edges.size() == 0) {
            //Return a random node.
            return nodes.get((int) (Math.random() * nodes.size()));
        } else {
            return node.edges.get((int) (Math.random() * node.edges.size()));
        }
    }
}

package PageRank;

import java.util.List;

/**
 * Created by oskar on 2016-09-29.
 */
public class RandomSurfer {
    public static void main(String[] args){

        String path = "src/PageRank/Data/three.txt";

        List<Node> nodes = Parser.parse(path);

        double alpha = 85.0/100;

    }
}

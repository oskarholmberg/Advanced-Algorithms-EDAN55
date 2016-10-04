package TreeWidth;

import java.util.List;

/**
 * Created by oskar on 2016-10-04.
 */
public class Main {
    public static void main(String[] args){
        Parser parser = new Parser();
        List<Bag> bags = parser.parse("src/TreeWidth/data/AhrensSzekeresGeneralizedQuadrangleGraph_3");
        for(int k = 1; k <= bags.size(); k++){
            System.out.print("b " + k);
            for(int i = 0; i < bags.get(k-1).nodes.size(); i++){
                System.out.print(" " + bags.get(k-1).nodes.get(i).id);
            }
            System.out.println();
        }
    }
}

package TreeWidth;

import java.util.Set;

/**
 * Created by oskar on 2016-10-04.
 */
public class Main {
    public static void main(String[] args){
        Parser parser = new Parser();
        Set<Bag> bags = parser.parse("src/TreeWidth/data/ChvatalGraph");
    }
}

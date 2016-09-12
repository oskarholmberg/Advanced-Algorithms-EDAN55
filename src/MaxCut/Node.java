package MaxCut;

/**
 * Created by oskar on 9/12/16.
 */
public class Node {

    private int id;
    public int location;

    public Node(int id) {
        this.id = id;
    }

    public boolean equals(Object obj) {
        return obj instanceof Node && ((Node) obj).getId() == id;
    }

    public int getId() {
        return id;
    }
}

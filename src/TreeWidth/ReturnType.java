package TreeWidth;

import java.util.List;
import java.util.Set;

public class ReturnType {
	public Set<Node> independentSet;
	public int value;

	public ReturnType(Set<Node> independentSet, int value) {
		this.independentSet = independentSet;
		this.value = value;
	}

}
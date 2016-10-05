package TreeWidth;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Algorithm {

	public static Set<Bag> bags;
	
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		bags = new HashSet<>(parser.parse("src/TreeWidth/data/BalancedTree_3_5"));

		for (Bag bag : bags) {
			bag.calculateSolutions();
		}

		Bag selectedBag = bags.iterator().next();
		selectedBag.createTree();
		selectedBag.calcIndependentSet();
		
		for (Bag b : bags){
			b.printFamily();
		}
		
		Collection<Node> best = selectedBag.getBest();
		
		System.out.print("selected nodes: ");
		for (Node n : best){
			System.out.print(n.id + " ");
		}
		System.out.println();
		System.out.println("total: " + best.size());



	}
	

}

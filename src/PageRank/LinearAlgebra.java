package PageRank;

import java.util.List;

import org.jblas.FloatMatrix;

public class LinearAlgebra {
    public int[][] adjecency;
    public int[][] hyperlink;
    public static void main(String[] args){

        //FloatMatrix floatMatrix = Parser.parse("src/PageRank2/Data/three.txt");
    	FloatMatrix floatMatrix = LinearAlgebra.getMatrix("src/PageRank2/Data/three.txt");
        for (int i = 0; i < floatMatrix.rows; i++){
            for (int j = 0; j < floatMatrix.columns; j++){
                System.out.print(floatMatrix.get(i, j) + " ");
            }
            System.out.println();
        }

        
    }

	
	public static FloatMatrix getMatrix(String path){
		List<Node> nodes = Parser.parse(path);
		
		FloatMatrix outMatrix = new FloatMatrix(nodes.size(), nodes.size());
		for (int i = 0; i < nodes.size(); i++){
			Node currNode = nodes.get(i);
			float times[]  = new float[nodes.size()];
			for (int k = 0; k < times.length; k++){
				times[k] = 0;
			}
			for (Node n : currNode.edges){
				times[n.id] += (1f / currNode.edges.size());
			}
			
			for (int j = 0; j < times.length; j++){
				outMatrix.put(i, j, times[j]);
			}
//			for (int j = 0; j < nodes.size(); j++){

//			}
		}
		return outMatrix;
		
	}
	
}

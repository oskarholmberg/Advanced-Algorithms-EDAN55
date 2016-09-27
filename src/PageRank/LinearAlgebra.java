package PageRank;

import java.util.List;

public class LinearAlgebra {
	
	
	public float[][] getMatrix(String path){
		List<Node> nodes = Parser.parse(path);
		
		float[][] outMatrix = new float[nodes.size()][];
		for (int i = 0; i < nodes.size(); i++){
			outMatrix[i] = new float[nodes.size()];
			for (int j = 0; j < nodes.size(); j++){
				
			}
		}
		return outMatrix;
		
	}
	
}

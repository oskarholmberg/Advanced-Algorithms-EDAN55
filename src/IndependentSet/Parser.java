package IndependentSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import MaxCut.Edge;
import MaxCut.Node;

public class Parser {
	
	int size;
	int[][] neighbours;
	
	public Parser(String path) {
		parse(path);
		
		}

	
	public void parse(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            line = br.readLine();
            String[] data;
            int currentLine = 0;
            while (line != null) {
                data = line.split(" ");
                if (currentLine == 0) {
                	size = Integer.parseInt(data[0]);
                } else {
                	for (int i = 0; i < data.length; i++) {
                		neighbours[currentLine-1][i] = Integer.parseInt(data[0]);
                	}              	
                }
                currentLine++; 
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public int getSize() {
		return size;
	}

	public int[] getNeighbours(int node) {
		return neighbours[node];
	}
}

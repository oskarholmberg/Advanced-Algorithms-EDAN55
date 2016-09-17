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
            size = 0;
            while (line != null) {
            	size++;
                data = line.split(" ");
                
 
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	
	
}

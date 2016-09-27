package PageRank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Parser {

	
	public static List<Node> parse(String path){
		List<Node> nodes = new ArrayList<Node>();
		
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			int size = Integer.parseInt(line);
			
			for (int i = 0; i < size; i++){
				nodes.add(new Node());
			}
			
			line = br.readLine();
			String[] data;
			while (line != null) {
				data = line.split(" ");
				for (int i = 0; i < data.length; i+=2){
					
				}
				line = br.readLine();
			}
		} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
}

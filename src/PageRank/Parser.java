package PageRank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Parser {

	public static List<Node> parse(String path) {
		List<Node> nodes = new ArrayList<Node>();

		BufferedReader br;
		try {
			System.out.println(path);

			br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			int size = Integer.parseInt(line.trim());
			System.out.println("nodes in graph: " + size);

			for (int i = 0; i < size; i++) {
				nodes.add(new Node(i));
			}

			line = br.readLine();
			while (line != null && !line.isEmpty()) {
				boolean readingNode = true;
				int baseNode = 0;
				int edgeNode;
				String[] lineData;
				if (line.contains("\t")){
					lineData = line.trim().split("\\t");
				} else{
					lineData =  line.trim().split(" ");

				}
				for (int i = 0; i < lineData.length; i++) {

					try {
						if (readingNode){
							baseNode = Integer.parseInt(lineData[i]);
							readingNode = false;
						}
						else {
							edgeNode = Integer.parseInt(lineData[i]);
							nodes.get(baseNode).edges.add(nodes.get(edgeNode));
							//System.out.println("adding: " + baseNode + " " + edgeNode);
							readingNode = true;
						}
					} catch (Exception e) {
						continue;
					}
				}
//				System.out.println(line);
				line = br.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return nodes;

	}
}

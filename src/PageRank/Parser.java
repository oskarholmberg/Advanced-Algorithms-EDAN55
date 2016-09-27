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
			System.out.println(size);

			for (int i = 0; i < size; i++) {
				nodes.add(new Node());
			}

			line = br.readLine();
			while (line != null && !line.isEmpty()) {
				boolean readingNode = true;
				int baseNode = 0;
				int edgeNode;
				String[] lineData = line.trim().split(" ");
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
			// line = line.trim();
			// line.replace(" ", " ");
			// line.replace(" ", " ");
			// System.out.println(line);
			// edgesData = line.split(" ");
			// List<Edge> edges = new ArrayList<Edge>();
			//
			// for (int i = 0; i < edgesData.length; i++) {
			// edgesData[i] = edgesData[i].replace(" ", " ");
			// edgeData = edgesData[i].trim().split(" ");
			// edges.add(new Edge(nodes.get(Integer.parseInt(edgeData[1]))));
			// }
			// line = br.readLine();
			// }
			// br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return nodes;

	}
}

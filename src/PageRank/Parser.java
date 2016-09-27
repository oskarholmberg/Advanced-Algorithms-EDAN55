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
			int size = Integer.parseInt(line);
			System.out.println(size);

			for (int i = 0; i < size; i++) {
				nodes.add(new Node());
			}

			line = br.readLine();
			String[] edgesData;
			String[] edgeData;
			int currNode = 0;
			while (line != null && !line.isEmpty()) {
				line = line.trim();
				line.replace("     ", "    ");
				line.replace("    ", "   ");
				System.out.println(line);
				edgesData = line.split("   ");
				List<Edge> edges = new ArrayList<Edge>();

				for (int i = 0; i < edgesData.length; i++) {
					edgesData[i] = edgesData[i].replace("  ", " ");
					edgeData = edgesData[i].trim().split(" ");
					edges.add(new Edge(nodes.get(Integer.parseInt(edgeData[1]))));
				}
				String[] data;
				while (line != null) {
					data = line.split(" ");
					for (int i = 0; i < data.length; i += 2) {

					}
					nodes.get(currNode).edges = edges;
					line = br.readLine();
					currNode++;
				}
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nodes;

	}
}

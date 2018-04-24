/* This class creates a new graph with only the vertices that need
 *  to be included in the Steiner Tree. All the edges are replaced by the shortest 
 *  distance between those two edges.
 */

public class ShortestPathGraph {
	
	final static int INF = 99999;
	static int newV;
	Matrixstats matrix;
	// Matrix used to store the new graph G'.
	int sp_graph[][];
	Vertex vert;

	public ShortestPathGraph(int V, Matrixstats matrix, Vertex vert) {
		ShortestPathGraph.newV = V;
		sp_graph = new int[newV][newV];
		this.matrix = matrix;
		this.vert = vert;
	}

	// Create the graph by filling the matrix used the distance[][] matrix 
	// output from FlyodWarshall Algorithm.
	public void create_sp_graph() {
		for (int i = 0; i < newV; ++i) {
			for (int j = 0; j < newV; ++j) {
				sp_graph[i][j] = matrix.distance[vert.newV[i]][vert.newV[j]];
			}
		}
	}

	public void print_newgraph() {
		// Print the derived new shortest path graph.
		System.out.println();
		System.out.println("Following matrix shows derivative graph");
		for (int i = 0; i < newV; i++) {
			for (int j = 0; j < newV; j++) {
				if (sp_graph[i][j] == INF)
					System.out.print("INF\t");
				else
					System.out.print(sp_graph[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

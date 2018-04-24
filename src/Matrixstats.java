// Class to initalize the store the shortest path detail of the original graph.
public class Matrixstats {

	// Declaration of the arrays.
	static int V;
	int distance[][];
	int nexthop[][];
	int original[][];
	final static int INF = 99999;

	// Constructor to initialize the values of the matrices.
	public Matrixstats(int V, int graph[][]) {
		Matrixstats.V = V;
		int i, j;
		original = graph;
		distance = new int[V][V];
		nexthop = new int[V][V];

		// Initialize the distance matrix same as input graph matrix.
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				distance[i][j] = graph[i][j];

		// Initialize the next hop as the direct connected edge.
		// If there is no edge between the two vertices next hop is -1 to
		// signify this.
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				if (distance[i][j] == INF)
					nexthop[i][j] = -1;
				else
					nexthop[i][j] = j;
	}

	public void print_matrices() {

		// Print the original graph.
		System.out.println("Following matrix shows the orginal graph");
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (original[i][j] == INF)
					System.out.print("INF\t");
				else
					System.out.print(original[i][j] + "\t");
			}
			System.out.println();
		}
		
		// Print the shortest distance matrix.
		System.out.println("Following matrix shows the shortest "
				+ "distances between every pair of vertices");
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				if (distance[i][j] == INF)
					System.out.print("INF\t");
				else
					System.out.print(distance[i][j] + "\t");
			}
			System.out.println();
		}

		// Print the next hop matrix.
		System.out.println("Following matrix shows the next hop "
				+ "for shortest distance between every pair of vertices");
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				System.out.print(nexthop[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

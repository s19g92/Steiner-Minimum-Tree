/* This class implemetns Floyd Warshall all pair algorithm to find out the 
 *  shortest distance and path between any two vertices in the Graph [V,E]
 *  given the adjaceny matrix on the graph.
 *  
 *  Author: SHUBHAM GUPTA
 */

class FloydWarshall {
	// Set Infinity value declaration.
	final static int INF = 99999;
	Matrixstats matrix;
	static int V;

	// Set the number of vertices in the graph and the matrix data structure.
	public FloydWarshall(int No_vertex, Matrixstats matrix) {
		FloydWarshall.V = No_vertex;
		this.matrix = matrix;
	}

	// Function to compute the shortest distance and the next hop for all vertices.
	Matrixstats shortestDistance(int graph[][]) {
		int i, j, k;

		/*
		 * Add all vertices one by one to the set of intermediate vertices.
		 * 
		 * (1) Before start of a iteration, we have shortest distances between
		 * all pairs of vertices such that the shortest distances consider only
		 * the vertices in set {0, 1,.. k-1} as intermediate vertices. (2) After
		 * the end of a iteration, vertex number k is added to the set of
		 * intermediate vertices and the set becomes {0, 1,.. k}
		 */
		for (k = 0; k < V; k++) {
			// Pick all vertices as source one by one
			for (i = 0; i < V; i++) {
				// Pick all vertices as destination for the
				// above picked source
				for (j = 0; j < V; j++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j] and next hop
					if (matrix.distance[i][k] + matrix.distance[k][j] < matrix.distance[i][j]) {
						matrix.distance[i][j] = matrix.distance[i][k]
								+ matrix.distance[k][j];
						matrix.nexthop[i][j] = k;
					}
				}
			}
		}
		return matrix;
	}

	// Main program to calculate the Steiner Minimum Tree (SMT).
	public static void main(String[] args) {
		
		int V = 9;
		/* Create the dummy input graph.		
        		2    3
    		(0)--(1)--(2)
     		|   / \   |
    	   6| 8/   \5 |7
     		| /     \ |
    		(3)-------(4)
          		9          */
		int graph[][] = {{INF, 8, 9, INF, INF, 2, INF, 2 ,INF},
                {8, INF, INF, INF, 5, 2, INF, INF, 8},
                {9, INF, INF, 8, INF, INF, 4, 8, INF},
                {INF, INF, 8, INF, INF, INF, 3, INF, INF},
                {INF, 5, INF, INF, INF, INF, 5, INF, 8},
                {2, 2, INF, INF, INF, INF, 1, INF, INF},
                {INF, INF, 4, 3, 5, 1, INF, INF ,INF, INF},
                {2, INF, 8, INF, INF, INF, INF, INF, INF},
                {INF, 8, INF, INF, 8, INF, INF, INF, INF}
               };
		
		// Set of the vertices to be included in the SMT.
		int Nodes[] = {0,1,2,3,4};
		
		// Matrix class used to store the data (input and output).
		Matrixstats matrix = new Matrixstats(V, graph);
		FloydWarshall pair = new FloydWarshall(V, matrix);

		// Print the shortest distances and next hop.
		matrix = pair.shortestDistance(graph);
		matrix.print_matrices();
		
		// Start the second step of creating the derivative graph.
		int newV = Nodes.length;
		Vertex vert;
		vert = new Vertex(newV);
		vert.newV = Nodes;
		vert.print_newVertex();	
		ShortestPathGraph derive = new ShortestPathGraph(newV, matrix, vert);
		
		// Create and print the derivative graph matrix.
		derive.create_sp_graph();
		derive.print_newgraph();
		
		//Make a mimimum spanning tree on the newly derived graph.
		MST mst = new MST(newV);
		int parent[];
		parent = mst.primMST(derive.sp_graph);
		
		//Replace all the minimum edges with their original path to get the SMT
		SMT smt = new SMT(V);
		smt.final_tree(matrix,vert, parent);
		}
}

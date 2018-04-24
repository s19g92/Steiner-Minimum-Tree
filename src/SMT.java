/* This class is used to return the MST tree to its orginal form
 * of SMT.
 */

public class SMT {

	final static int INF = 99999;
	int inc[];

	public SMT(int v) {
		// Array to store if a vertex is already included in the tree or not.
		inc = new int[v];
	}

	public void final_tree(Matrixstats matrix, Vertex vert, int[] parent) {

		// Check all the edges and replace with their respective
		// original paths.

		System.out.println("Edge   Weight of the Steiner MT");
		for (int i = 1; i < parent.length; i++) {

			boolean br = false;
			// If the shortest path is the direct edge between two terminals
			// include the edge.
			if (matrix.nexthop[vert.newV[parent[i]]][vert.newV[i]] == vert.newV[i]) {
				// Only include the edge if both vertices are not present
				if (inc[vert.newV[parent[i]]] != 1 || inc[vert.newV[i]] != 1) {
					System.out
							.println((vert.newV[parent[i]])+1
									+ " - "
									+ (vert.newV[i]+1)
									+ "    "
									+ matrix.original[vert.newV[parent[i]]][vert.newV[i]]);
					
					inc[vert.newV[parent[i]]] = 1;
					inc[vert.newV[i]] = 1;
				}
				
			} else {
				// If the shortest path is not a direct edge then include edges
				// one by one if they dont form cycles.
				int j;
				do { 
					j = matrix.nexthop[vert.newV[parent[i]]][vert.newV[i]];
					// Only include the edge if both vertices are not present
					if (inc[parent[i]] != 1 || inc[j] != 1) {
						System.out.println(vert.newV[parent[i]]+ 1 + " - " + (j+1)
								+ "    "
								+ matrix.original[vert.newV[parent[i]]][j]);
						inc[vert.newV[parent[i]]] = 1;
						inc[j] = 1;
						vert.newV[parent[i]] = j;
					} else {
						vert.newV[parent[i]] = j;
					}
					
					// Continue the do while loop till last hop is the node itself
					if (matrix.nexthop[j][vert.newV[i]] == vert.newV[i]) {
						// Only include the edge if both vertices are not present
						if (inc[j] != 1 || inc[vert.newV[i]] != 1) {
							System.out
									.println((j+1) + " - " + (vert.newV[i]+1) + "    "
											+ matrix.original[j][vert.newV[i]]);
							inc[j] = 1;
							inc[vert.newV[i]] = 1;
							br = true;
						} 
					}
				} while (!br);
				br = false;
			}
		}
	}
}

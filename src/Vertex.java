/* A class to keep track of the vertex number relation between 
*  the original given graph and the newly created shortest path
*  graph/
*/

public class Vertex {
	public int newV[];
	int V;
	
	public Vertex(int V){
		this.V = V;
		newV = new int[V];
	}
	
	public void print_newVertex(){
		System.out.println();
		System.out.println("Following are the new vertex numbers");
		for(int i=0; i<V;i++){
			System.out.print(newV[i]+"\t");
		}
	}
}

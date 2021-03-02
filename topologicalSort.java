import java.util.*;

class Graph{
    int vertices;
    List<Integer> adjacent[];
    // *list is interface which is implemented by different classes like stack, liked list, Array, Vector

    //?constructor
    public Graph(int v) {
        this.vertices = v;
        adjacent = new ArrayList[v];
        for(int i = 0; i < v; i++){
            adjacent[i] = new ArrayList<Integer>(); // *Array list implements List interface which can be instantiated which workd like auto balencing
        }
    }
    //? add edges
    public void addEdge(int U, int V){
        adjacent[U].add(V);
    }
    // ? topological sort
    public Vector<Integer> topologicalSort(){

        int indegree[] = new int[this.vertices];
        
        for(int i = 0; i < this.vertices; i++){
            for(int j = 0; j < adjacent[i].size(); j++){
                indegree[adjacent[i].get(j)]++;
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < this.vertices; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        Vector<Integer> topOrder = new Vector<Integer>();
        int count = 0;
        while(!q.isEmpty()){

            int u = q.poll(); //* this just returns front item of the container

            topOrder.add(u); //* adding the element in the sorted order

            for(int node : adjacent[u]) //* decresing th indegree of adjacent nodes and if any one got 0 indegree
                if(--indegree[node] == 0) //* add it to queue
                    q.add(node); 

        }
        // if(count != this.vertices){
            return topOrder; //* this indicates that we cant able travel every node this condition says it has cycle in the graph
        // }
        // return Collections.reverse(topOrder);
    }
}
class Main { 
    public static void main(String args[]) 
    { 
        // *Create a graph given in the above diagram 
        Graph g = new Graph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
        g.addEdge(1, 2);
        Vector<Integer> top = g.topologicalSort();
        if(top.size() == 6){
            System.out.println( "Following is a Topological Sort"); 
            for (int i : top) { 
                System.out.print(i + " "); 
            } 
        }
        else{
            System.out.print("detected cycle");
        }
    } 
} 
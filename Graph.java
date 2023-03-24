import java.util.ArrayList;
import java.util.List;

/**
 * It's a class that represents a graph in list form
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class Graph {

    // It's a variable that represents the number of vertices in the graph.
    private final int n;
    // It's a variable that represents the graph in list form.
    private final ArrayList<ArrayList<Integer>> graphInListForm;

    Graph(int n) {
        this.n = n;
        graphInListForm = new ArrayList<>();
        // It's a loop that creates an empty list for each vertex.
        for (int i = 0; i < this.n; i++) {
            graphInListForm.add(new ArrayList<>());
        }
    }

    /**
     * Add an edge from vertex i to vertex j.
     *
     * @param i The index of the vertex from which the edge is outgoing.
     * @param j The index of the vertex that you want to add an edge to.
     *          <p>
     *          pre: graph with edges has been generated and allocated memory
     *          <p>
     *          post: edges has been stored in graph and allocated memory
     */
    public void addEdge(int i, int j) {
        graphInListForm.get(i).add(j);
    }

    /**
     * This function returns the graph in list form.
     *
     * @return The graph in list form.
     * <p>
     * pre: residual graph allocated in memory
     * <p>
     * post: returns the graph in list form
     */
    public List<ArrayList<Integer>> getGraphInListForm() {
        return graphInListForm;
    }
}

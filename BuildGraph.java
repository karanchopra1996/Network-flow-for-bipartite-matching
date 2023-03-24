import java.util.ArrayList;
import java.util.List;

/**
 * It takes in a graph and returns a residual graph
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class BuildGraph {
    /**
     * It takes a graph with an even number of nodes, and returns a graph with an odd number of nodes, where the odd
     * node is the one that is connected to all the even nodes
     *
     * @param numberOfNodes The number of nodes in the graph.
     * @param edges         a 2D array of integers, where each row represents an edge.
     *
     * @return A graph with the edges.
     * <p>
     * pre: input file data has been allocated memory
     * <p>
     * post: return the graph with the edges
     */
    public Graph generateGraph(int numberOfNodes, int[][] edges) {
        // Creating a graph with an odd number of nodes, where the odd node is the one that is connected to all the even
        // nodes.
        Graph graph = new Graph(numberOfNodes + 2);
        int matches = numberOfNodes / 2;
        // A for each loop. It is iterating over the edges array.
        for (int[] edge : edges) {
            int left = edge[0];
            int right = edge[1];

            // Checking if the left node is in the matching and the right node is not in the matching.
            if (left <= matches && right > matches) {
                graph.addEdge(left, right);
            } else if (left > matches && right <= matches) graph.addEdge(right, left);
        }

        return graph;
    }

    /**
     * The function takes a graph as input and returns a residual graph of the input graph
     *
     * @param g The graph object
     *
     * @return The residual graph is being returned.
     * <p>
     * pre: graph with edges has been generated and allocated memory
     * <p>
     * post: return the residual graph
     */
    public List<ArrayList<Integer>> createResidualGraph(Graph g) {
        List<ArrayList<Integer>> graph = g.getGraphInListForm();

        // Adding the edges from the source to the nodes that are in the matching.
        for (int i = 1; i <= graph.size() / 2; i++) {
            if (!graph.get(i).isEmpty()) {
                graph.get(0).add(i);
            }
        }

        // Adding an edge from the nodes that are not in the matching to the sink.
        for (int i = graph.size() / 2; i < graph.size() - 1; i++) {
            if (graph.get(i).isEmpty()) {
                graph.get(i).add(graph.size() - 1);
            }
        }

        return graph;
    }
}

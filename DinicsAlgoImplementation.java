import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * It takes a residual graph as input and prints all the paths from source to sink
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class DinicsAlgoImplementation {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    private List<String> names;

    /**
     * The function takes in a residual graph, a list of names, and a list of integers. It then sets the names to the
     * list of names, creates a new list of integers, adds 0 to the list, and calls the augmentFlow function with the
     * residual graph, the list of integers, and 0
     *
     * @param residualGraph The residual graph of the original graph.
     * @param names         a list of names of the vertices
     *                      <p>
     *                      pre: file data, residual graph have been allocated in memory
     *                      <p>
     *                      post: initialize the graph
     */
    public void initialize(List<ArrayList<Integer>> residualGraph, List<String> names) {
        this.names = names;
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0);
        augmentFlow(0, residualGraph, temp);
    }

    /**
     * If we're at the end of the graph, remove the edges from the graph and add the path to the result. If we're not at
     * the end of the graph, if there are edges to traverse, traverse them. If there are no edges to traverse,
     * backtrack
     *
     * @param sv            source vertex
     * @param residualGraph The graph that we are currently working on.
     * @param temp          This is the current path that we are exploring.
     *                      <p>
     *                      pre: file data, residual graph have been allocated in memory
     *                      <p>
     *                      post: initialize the graph
     */
    private void augmentFlow(int sv, List<ArrayList<Integer>> residualGraph, ArrayList<Integer> temp) {
        // Checking if we are at the end of the graph.
        if (sv == residualGraph.size() - 1) {
            // It removes the edges from the residual graph that are part of the augmenting path
            removeEdgesFromGraph(residualGraph, temp);
            res.add(new ArrayList<>(temp));
            temp.clear();
            temp.add(0);
            // Calling the augmentFlow function with the source vertex, the residual graph, and the list of vertices
            // that we
            // have visited so far.
            augmentFlow(0, residualGraph, temp);
        } else {
            // Checking if there are any edges to traverse.
            if (!residualGraph.get(sv).isEmpty()) {
                // Iterating through the adjacency list of the vertex sv.
                for (int node : residualGraph.get(sv)) {
                    temp.add(node);
                    // Calling the augmentFlow function with the source vertex, the residual graph, and the list of
                    // vertices
                    // that we
                    // have visited so far.
                    augmentFlow(node, residualGraph, temp);
                }
            } else {
                // Backtracking to the previous vertex.
                backtrack(sv, residualGraph, temp);
            }
        }
    }

    /**
     * If the vertex is the source, print the output and exit. Otherwise, remove all edges incident on the vertex and
     * backtrack to the previous vertex
     *
     * @param v             The vertex we are currently at.
     * @param residualGraph The residual graph
     * @param temp          This is the list of vertices that we have visited so far.
     *                      <p>
     *                      pre: graph has been visited and allocated memory
     *                      <p>
     *                      post: backtracks the graph to the previous vertex
     */
    private void backtrack(int v, List<ArrayList<Integer>> residualGraph, ArrayList<Integer> temp) {
        if (v == 0) {
            // It prints out the names of the two people who are the most similar, and then prints out the total
            // number of
            // matches
            printOutput(res);
            // It exits the program.
            System.exit(0);
        } else {
            // It removes all edges incident on vertex v from the residual graph.
            removeEdgesIncidentOnVertex(residualGraph, v);
            // It removes the last element from the list.
            temp.remove(temp.size() - 1);
            // Calling the augmentFlow function with the source vertex, the residual graph, and the list of vertices
            // that we
            // have visited so far.
            augmentFlow(temp.get(temp.size() - 1), residualGraph, temp);
        }
    }

    /**
     * Remove all edges incident on vertex v from the residual graph.
     *
     * @param residualGraph The residual graph
     * @param v             The vertex to be removed from the graph.
     *                      <p>
     *                      pre: graph has been visited and allocated memory
     *                      <p>
     *                      post: removed vertex v is in the graph
     */
    private void removeEdgesIncidentOnVertex(List<ArrayList<Integer>> residualGraph, int v) {
        // Iterating through the residual graph.
        for (int i = 0; i <= (residualGraph.size() - 1); i++) {
            // Skipping the vertex that we are currently at.
            if (i == v) continue;
            // Getting the adjacency list of the vertex i.
            List<Integer> adjList = residualGraph.get(i);
            // Iterating through the adjacency list of the vertex i.
            for (int j = 0; j < adjList.size(); j++) {
                if (adjList.get(j) == v) adjList.remove(j);
            }
        }
    }

    /**
     * It removes the edges from the residual graph that are part of the augmenting path
     *
     * @param residualGraph The residual graph of the original graph.
     * @param temp          This is the list of vertices that form the path.
     *                      <p>
     *                      pre: graph has been visited and allocated memory
     *                      <p>
     *                      post: removed the edges from the residual graph
     */
    private void removeEdgesFromGraph(List<ArrayList<Integer>> residualGraph, ArrayList<Integer> temp) {
        // Iterating through the list of vertices that form the path.
        for (int i = 0; i < temp.size() - 1; i++) {
            // Getting the adjacency list of the vertex that is at index i in the list of vertices that form the path.
            ArrayList<Integer> adjacencyList = residualGraph.get(temp.get(i));
            // Iterating through the adjacency list of the vertex i.
            for (int j = 0; j < adjacencyList.size(); j++) {
                // Checking if the vertex that is at index j in the adjacency list of the vertex i is equal to the
                // vertex
                // that is at index i+1 in the list of vertices that form the path.
                if (Objects.equals(adjacencyList.get(j), temp.get(i + 1))) {
                    // It removes the edge from the residual graph.
                    adjacencyList.remove(j);
                    break;
                }
            }
        }
    }

    /**
     * It prints out the names of the two people who are the most similar, and then prints out the total number of
     * matches
     *
     * @param res the list of matches
     *            <p>
     *            pre: matched hs been found and stored in memory
     *            <p>
     *            post: Outputs the matches in the order that the “left” items appear in the file and the total number
     *            of matches.
     */
    public void printOutput(List<ArrayList<Integer>> res) {
        // Iterating through the list of matches.
        for (List<Integer> path : res) {
            System.out.println(names.get(path.get(1) - 1) + " / " + names.get(path.get(2) - 1));
        }
        System.out.println(res.size() + " total matches");
    }
}

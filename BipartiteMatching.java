import java.util.ArrayList;
import java.util.List;

/**
 * It reads the input file, builds the graph, creates the residual graph and then calls the Dinics algorithm
 * implementation class and outputs the matches
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class BipartiteMatching {
    /**
     * The main function reads the input file, builds the graph, creates the residual graph, and initializes the Dinics
     * algorithm
     * <p>
     * pre: none
     * <p>
     * post: Output the matches in the order that the “left” items appear in the file and the total number of matches.
     */
    public static void main(String[] args) {
        String filePath = "program3data.txt";
        try {
            // Reading the input file and storing the data in the file object.
            ReadInputFile file = new ReadInputFile(filePath);
            BuildGraph buildGraph = new BuildGraph();
            // Creating an object of the DinicsAlgoImplementation class.
            DinicsAlgoImplementation dinics = new DinicsAlgoImplementation();
            // Creating a graph object and initializing it with the number of nodes and edges.
            Graph graph = buildGraph.generateGraph(file.getN(), file.getEdges());
            // Creating a residual graph from the graph.
            List<ArrayList<Integer>> residualGraph = buildGraph.createResidualGraph(graph);
            // Calling the initialize method of the DinicsAlgoImplementation class.
            dinics.initialize(residualGraph, file.getNames());
        }
        catch (Exception e) {
            System.err.println("Error !!! The provided data file or file path is incorrect.");
            e.printStackTrace();
        }
    }
}

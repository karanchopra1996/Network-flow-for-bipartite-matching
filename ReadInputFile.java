import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * It reads the input file and stores the points in an array
 *
 * @author Ashish Nagar <anagar@uw.edu> & Karan Chopra <karanc4@uw.edu>
 */
public class ReadInputFile {
    // A variable that stores the number of nodes.
    int n;
    // A variable that stores the number of edges.
    int numberOfEdges;
    // Creating a list of strings.
    List<String> names = new ArrayList<>();
    // Creating a 2D array of edges.
    int[][] edges;

    /**
     * Function to read and to store input file data - number of nodes, names, edges
     * <p>
     * pre: none
     * <p>
     * post: read and store input file data to data structure variables
     */
    public ReadInputFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Setting the value of the variable n to the value of the parameter n.
            setN(scanner.nextInt());
            // Reading the names of the nodes from the input file and storing them in a list.
            readNames(scanner, getN());
            // It reads the number of edges from the scanner and sets the number of edges.
            readNumberOfEdges(scanner);
            // It reads the edges from the input file and stores them in a 2D array
            readEdges(scanner, getNumberOfEdges());
        }
        catch (IOException e) {
            System.err.println("Error !!! The provided data file or file path is incorrect.");
            e.printStackTrace();
        }
    }

    /**
     * It reads the edges from the input file and stores them in a 2D array
     *
     * @param scanner       The scanner object that reads the input file.
     * @param numberOfEdges The number of edges in the graph.
     *                      <p>
     *                      pre: nodes, names and number of edges in the graph are stored in memory.
     *                      <p>
     *                      post: connected edges are stored in memory
     */
    private void readEdges(Scanner scanner, int numberOfEdges) {
        edges = new int[numberOfEdges][2];
        for (int i = 0; i < numberOfEdges; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            edges[i][0] = l;
            edges[i][1] = r;
        }
    }

    /**
     * Reads the number of edges from the scanner and sets the number of edges.
     *
     * @param scanner The scanner object that is used to read the input file.
     *                <p>
     *                pre: none
     *                <p>
     *                post: read and store input file data to data structure variables
     */
    private void readNumberOfEdges(Scanner scanner) {
        setNumberOfEdges(scanner.nextInt());
    }

    /**
     * Read n names from the scanner and add them to the names list.
     *
     * @param scanner The scanner object that will be used to read the input.
     * @param n       the number of names to read
     *                <p>
     *                pre: number of nodes allocated memory
     *                <p>
     *                post: read and store names to List
     */
    private void readNames(Scanner scanner, int n) {
        for (int i = 1; i <= n; i++) {
            names.add(scanner.next());
        }
    }

    /**
     * This function returns the value of the variable n.
     *
     * @return The value of the variable n.
     * <p>
     * pre: number of nodes variable stored in memory
     * <p>
     * post: number of nodes returned
     */
    public int getN() {
        return n;
    }

    /**
     * This function sets the value of the variable n to the value of the parameter n.
     *
     * @param n The number of points to be generated.
     *          <p>
     *          pre: none
     *          <p>
     *          post: number of edges allocated memory
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This function returns the number of edges in the graph
     *
     * @return The number of edges in the graph.
     * <p>
     * pre: nodes, names and number of edges in the graph are stored in memory.
     * <p>
     * post: number of edges returned
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    /**
     * This function sets the number of edges in the graph.
     *
     * @param numberOfEdges The number of edges in the graph.
     *                      <p>
     *                      pre: nodes, names variables are stored in memory.
     *                      <p>
     *                      post: number of edges allocated memory
     */
    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    /**
     * This function returns the edges of the graph.
     *
     * @return The edges of the graph.
     * <p>
     * pre: nodes, names and number of edges, and edges in the graph are stored in memory.
     * <p>
     * post: return the graph edges
     */
    public int[][] getEdges() {
        return edges;
    }

    /**
     * This function returns a list of strings.
     *
     * @return A list of strings
     * <p>
     * pre: nodes, names and number of edges, and edges in the graph are stored in memory.
     * <p>
     * post: return the names list of strings
     */
    public List<String> getNames() {
        return names;
    }
}

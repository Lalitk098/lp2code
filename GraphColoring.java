public class GraphColoring {
    /*
    V denotes the total number of vertices of the graph
    */

    static int V = 4;

    // defining a color array that stores the result.
    int colorArray[];


    /* A function to print the color configuration*/
    void printConfiguration(int colorArray[]) {
        System.out.println("The assigned colors are as follows: ");

        for (int i = 0; i < V; i++)
            System.out.println("Vertex: " + i + " Color: " + colorArray[i]);
    }


    /*
    A function that will check if the current color of the graph is safe or not.
    */
    boolean isSafe(
            int v, int graph[][], int colorArray[],
            int vertex) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && vertex == colorArray[i])
                return false;
        return true;
    }

    /*
    A recursive function that takes the current index, number of vertices, and the color array. If the recursive call returns true then the coloring is possible. It returns
    false if the m colors cannot be assigned.
    */
    boolean graphColoringAlgorithmUtil(
            int graph[][], int m,
            int colorArray[], int currentVertex) {
    
        // base case
        if (currentVertex == V)
            return true;

        for (int i = 1; i <= m; i++) {
            
            if (isSafe(currentVertex, graph, colorArray, i)) {
                colorArray[currentVertex] = i;
                if (graphColoringAlgorithmUtil(
                        graph, m,
                        colorArray, currentVertex + 1))
                    return true;

                // backtracking
                colorArray[currentVertex] = 0;
            }
        }

        
        return false;
    }

    boolean graphColoringAlgorithm(int graph[][], int m) {
        /*
        Initially, the color array is initialized with 0.
        */
        colorArray = new int[V];
        for (int i = 0; i < V; i++)
            colorArray[i] = 0;

        // Call graphColoringAlgorithmUtil() for vertex 0
        if (!graphColoringAlgorithmUtil(graph, m, colorArray, 0)) {
            System.out.println("Coloring is not possible!");
            return false;
        }

        System.out.println("Coloring is possible!");
        printConfiguration(colorArray);
        return true;
    }

    public static void main(String args[]) {
        GraphColoring c = new GraphColoring();

        int graph[][] = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        int m = 3; 
        c.graphColoringAlgorithm(graph, m);
    }
}

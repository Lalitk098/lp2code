import java.util.*;

class PrimMST {
    private static final int V = 5; // Number of vertices in the graph

    // Function to find the vertex with minimum key value
    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Function to print the constructed MST stored in parent[]
    private void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // Function to construct and print the MST for a graph represented using adjacency matrix representation
    void primMST(int[][] graph) {
        int[] parent = new int[V]; // Array to store constructed MST
        int[] key = new int[V]; // Key values used to pick minimum weight edge in cut
        boolean[] mstSet = new boolean[V]; // To represent set of vertices not yet included in MST

        // Initialize all keys as INFINITE
        Arrays.fill(key, Integer.MAX_VALUE);

        // Always include first 1st vertex in MST
        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always the root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key values and parent index of the adjacent vertices of the picked vertex.
            // Consider only those vertices which are not yet included in the MST
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        printMST(parent, graph);
    }

    public static void main(String[] args) {
        /* Example graph represented using adjacency matrix */
        int[][] graph = { { 0, 2, 0, 6, 0 },
                          { 2, 0, 3, 8, 5 },
                          { 0, 3, 0, 0, 7 },
                          { 6, 8, 0, 0, 9 },
                          { 0, 5, 7, 9, 0 } };

        PrimMST mst = new PrimMST();
        mst.primMST(graph);
    }
}

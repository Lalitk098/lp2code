import java.util.LinkedList;
import java.util.Queue;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list representation of the graph

    @SuppressWarnings("unchecked")
    public Graph(int v) {
        V = v;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
    }

    public void BFS(int startVertex) {
        boolean[] visited = new boolean[V]; // Track visited vertices
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.remove();
            System.out.print(currentVertex + " ");

            LinkedList<Integer> neighbors = adjList[currentVertex];
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[V]; // Track visited vertices
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        LinkedList<Integer> neighbors = adjList[vertex];
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }


}

public class BFS {
    public static void main(String[] args) {
        Graph graph = new Graph(6); // Create a graph with 6 vertices

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.out.println("BFS traversal starting from vertex 0:");
        graph.BFS(0);
        System.out.println();
        System.out.println("DFS traversal starting from vertex 0:");
        graph.DFS(0);
    }
}

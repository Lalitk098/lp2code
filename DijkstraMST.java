import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraMST {
    private int vertices;
    private List<List<Node>> adjacencyList;

    public DijkstraMST(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Node(destination, weight));
        adjacencyList.get(destination).add(new Node(source, weight));
    }

    public int[] dijkstra(int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        minHeap.offer(new Node(source, 0));
        distances[source] = 0;

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if (currentWeight > distances[currentVertex]) {
                continue;
            }

            List<Node> neighbors = adjacencyList.get(currentVertex);
            for (Node neighbor : neighbors) {
                int newDistance = distances[currentVertex] + neighbor.weight;
                if (newDistance < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDistance;
                    minHeap.offer(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        return distances;
    }

    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        DijkstraMST graph = new DijkstraMST(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges and their weights in the format (source destination weight):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.print("Enter the source vertex for Dijkstra's algorithm: ");
        int sourceVertex = scanner.nextInt();

        int[] distances = graph.dijkstra(sourceVertex);

        System.out.println("Shortest distances from the source vertex:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": " + distances[i]);
        }

        scanner.close();
    }
}



// Enter the number of vertices: 5
// Enter the number of edges: 6
// Enter the edges and their weights in the format (source destination weight):
// 0 1 4
// 0 2 2
// 1 3 5
// 2 1 1
// 2 3 8
// 3 4 3
// Enter the source vertex for Dijkstra's algorithm: 0
// Shortest distances from the source vertex:
// Vertex 0: 0
// Vertex 1: 3
// Vertex 2: 2
// Vertex 3: 8
// Vertex 4: 11


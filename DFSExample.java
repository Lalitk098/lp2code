import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class DFSExample {
    private int vertices;
    private List<List<Integer>> adjacencyList;

    public DFSExample(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[vertices];

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();

            if (!visited[currentVertex]) {
                visited[currentVertex] = true;
                System.out.print(currentVertex + " ");

                List<Integer> neighbors = adjacencyList.get(currentVertex);
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }
    public void BFS(int startVertex) {
        boolean[] visited = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            List<Integer> neighbors = adjacencyList.get(currentVertex);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        DFSExample graph = new DFSExample(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges in the format (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();

        System.out.print("Depth First Traversal (DFS): ");
        graph.DFS(startVertex);

        System.out.println();
        System.out.print("Breadth First Traversal (BFS): ");
        graph.BFS(startVertex);

        scanner.close();
    }
}



// Enter the number of vertices: 6
// Enter the number of edges: 7
// Enter the edges in the format (source destination):
// 0 1
// 0 2
// 1 3
// 1 4
// 2 4
// 3 4
// 3 5
// Enter the starting vertex for DFS: 0
// Depth First Traversal (DFS): 0 2 4 1 3 5 
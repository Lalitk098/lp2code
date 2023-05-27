import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

class KruskalAlgorithm {
    private int V; // Number of vertices
    private ArrayList<Edge> edges;

    public KruskalAlgorithm(int v) {
        V = v;
        edges = new ArrayList<>();
    }

    private int findParent(int[] parent, int vertex) {
        if (parent[vertex] == vertex)
            return vertex;

        return findParent(parent, parent[vertex]);
    }

    private void union(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);
        parent[yParent] = xParent;
    }

    public void kruskalMST() {
        // Sort edges in ascending order
        Collections.sort(edges);

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> mst = new ArrayList<>();
        int edgeCount = 0;
        int index = 0;

        while (edgeCount < V - 1 && index < edges.size()) {
            Edge edge = edges.get(index);

            int srcParent = findParent(parent, edge.src);
            int destParent = findParent(parent, edge.dest);

            if (srcParent != destParent) {
                mst.add(edge);
                union(parent, srcParent, destParent);
                edgeCount++;
            }

            index++;
        }

        // Print the constructed MST
        System.out.println("Edge \tWeight");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + "\t" + edge.weight);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        KruskalAlgorithm algorithm = new KruskalAlgorithm(V);

        System.out.println("Enter the source, destination, and weight of each edge (separated by spaces):");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            algorithm.edges.add(new Edge(src, dest, weight));
        }

        algorithm.kruskalMST();

        scanner.close();
    }
}


// Enter the number of vertices: 6
// Enter the number of edges: 9
// Enter the source, destination, and weight of each edge (separated by spaces):
// 0 1 4
// 0 2 3
// 1 2 1
// 1 3 2
// 2 3 4
// 2 4 3
// 3 4 2
// 3 5 1
// 4 5 6
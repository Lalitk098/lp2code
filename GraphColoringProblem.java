import java.util.Arrays;

public class GraphColoringProblem {

    private int numVertices;
    private int numColors;
    private int[][] adjacencyMatrix;
    private int[] vertexColors;

    public GraphColoringProblem(int[][] adjacencyMatrix, int numColors) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;
        this.numColors = numColors;
        this.vertexColors = new int[numVertices];
    }

    public void solve() {
        if (backtrack(0)) {
            System.out.println("Graph coloring is possible!");
            System.out.println("Vertex colors: " + Arrays.toString(vertexColors));
        } else {
            System.out.println("Graph coloring is not possible!");
        }
    }

    private boolean backtrack(int vertexIndex) {
        if (vertexIndex == numVertices) {
            return true; // All vertices colored
        }

        for (int color = 1; color <= numColors; color++) {
            if (isColorValid(vertexIndex, color)) {
                vertexColors[vertexIndex] = color;

                if (backtrack(vertexIndex + 1)) {
                    return true;
                }

                // If the current coloring leads to a dead end, backtrack
                vertexColors[vertexIndex] = 0;
            }
        }

        return false;
    }

    private boolean isColorValid(int vertexIndex, int color) {
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[vertexIndex][i] == 1 && vertexColors[i] == color) {
                return false; // Adjacent vertices have the same color
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };
        int numColors = 3;

        GraphColoringProblem problem = new GraphColoringProblem(adjacencyMatrix, numColors);
        problem.solve();
    }
}

import java.util.*;

public class EightPuzzle {
    private final int[][] goalState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private final int[][] initialState;

    public EightPuzzle(int[][] initialState) {
        this.initialState = initialState;
    }

    public void solve() {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

        Node initialNode = new Node(initialState, 0, null);
        initialNode.calculateHeuristic(goalState);
        openList.add(initialNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);

            if (Arrays.deepEquals(currentNode.state, goalState)) {
                printSolutionPath(currentNode);
                return;
            }

            List<Node> neighbors = generateNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (closedList.contains(neighbor))
                    continue;

                neighbor.calculateHeuristic(goalState);
                neighbor.calculateCost();

                if (openList.contains(neighbor)) {
                    Node existingNode = findNodeInOpenList(openList, neighbor);
                    if (existingNode.cost <= neighbor.cost)
                        continue;

                    openList.remove(existingNode);
                }

                openList.add(neighbor);
            }
        }

        System.out.println("No solution found.");
    }

    private List<Node> generateNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[] blankPosition = findBlankPosition(node.state);
        int row = blankPosition[0];
        int col = blankPosition[1];

        if (row > 0) {
            int[][] newState = cloneState(node.state);
            swap(newState, row, col, row - 1, col);
            neighbors.add(new Node(newState, node.moves + 1, node));
        }

        if (row < 2) {
            int[][] newState = cloneState(node.state);
            swap(newState, row, col, row + 1, col);
            neighbors.add(new Node(newState, node.moves + 1, node));
        }

        if (col > 0) {
            int[][] newState = cloneState(node.state);
            swap(newState, row, col, row, col - 1);
            neighbors.add(new Node(newState, node.moves + 1, node));
        }

        if (col < 2) {
            int[][] newState = cloneState(node.state);
            swap(newState, row, col, row, col + 1);
            neighbors.add(new Node(newState, node.moves + 1, node));
        }

        return neighbors;
    }

    private int[] findBlankPosition(int[][] state) {
        int[] position = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    private int[][] cloneState(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newState[i][j] = state[i][j];
            }
        }
        return newState;
    }

    private void swap(int[][] state, int i1, int j1, int i2, int j2) {
        int temp = state[i1][j1];
        state[i1][j1] = state[i2][j2];
        state[i2][j2] = temp;
    }

    private Node findNodeInOpenList(PriorityQueue<Node> openList, Node targetNode) {
        for (Node node : openList) {
            if (Arrays.deepEquals(node.state, targetNode.state))
                return node;
        }
        return null;
    }

    private void printSolutionPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            printState(path.get(i).state);
            System.out.println();
            System.out.println("Move: " + path.get(i).moves);
            System.out.println();
        }
    }

    private void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Node implements Comparable<Node> {
        private final int[][] state;
        private int cost;
        private int heuristic;
        private final int moves;
        private final Node parent;

        public Node(int[][] state, int moves, Node parent) {
            this.state = state;
            this.moves = moves;
            this.parent = parent;
        }

        public void calculateHeuristic(int[][] goalState) {
            heuristic = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (state[i][j] != goalState[i][j])
                        heuristic++;
                }
            }
        }

        public void calculateCost() {
            cost = moves + heuristic;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(cost, other.cost);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(state);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            return Arrays.deepEquals(state, other.state);
        }
    }


    public static void main(String[] args) {
        int[][] initialState = {{1, 2, 3}, {4, 8, 0}, {7, 6, 5}};
        EightPuzzle puzzle = new EightPuzzle(initialState);
        puzzle.solve();
    }
}

// Here's a detailed explanation of how the code works:

// The EightPuzzle class represents the 8-puzzle problem solver.

// The goalState variable stores the desired state of the puzzle, which is a 3x3 grid with numbers 1 to 8 and an empty space represented by 0.

// The initialState is passed to the EightPuzzle constructor, representing the starting configuration of the puzzle.

// The solve method implements the A* search algorithm to find the solution to the 8-puzzle problem. It uses a priority queue called openList to store nodes that are candidates for expansion, and a set called closedList to store visited nodes.

// The Node class represents a state of the puzzle. Each node contains the state configuration, the cost to reach that state (cost), the heuristic value (heuristic), the number of moves taken so far (moves), and a reference to the parent node.

// In the solve method, an initial node is created with the initialState and added to the openList. The heuristic value for the initial node is calculated using the number of misplaced tiles heuristic.

// The algorithm enters a loop while the openList is not empty. In each iteration, it removes the node with the lowest cost from the openList (using the priority queue's poll method) and adds it to the closedList.

// If the removed node's state matches the goalState, it means the solution is found. The printSolutionPath method is called to print the solution path by traversing back through the parent nodes.

// If the goal state is not reached, the algorithm generates the neighboring nodes of the current node using the generateNeighbors method.

// The generateNeighbors method finds the position of the blank (0) in the current node's state. It creates new nodes by swapping the blank with neighboring tiles (up, down, left, right) if the swap is valid. The new nodes are added to the neighbors list.

// The calculateHeuristic method calculates the heuristic value for a node by counting the number of misplaced tiles in its state compared to the goalState.

// The calculateCost method updates the cost of a node by summing the number of moves (moves) and the heuristic value (heuristic).

// The algorithm checks if each neighbor is already in the closedList. If so, it skips further processing for that neighbor.

// For each neighbor, the heuristic value and cost are calculated. If the neighbor is already in the openList, its existing node is retrieved using the findNodeInOpenList method. If the existing node has a lower or equal cost, the neighbor is skipped. Otherwise, the existing node is removed from the openList to be replaced by the updated neighbor node.

// Finally, the updated neighbor node is added to the openList for further exploration.

// If the loop finishes without finding a solution, it means there is no valid solution for the given puzzle configuration. The message "No solution found" is printed.

// The findBlankPosition method finds the position of the blank (0) in a given state.

// The cloneState method creates a deep copy of a state array.

// The swap method swaps two elements in a state array.

// The findNodeInOpenList method searches for a node with a specific state in the openList and returns it if found.

// The printSolutionPath method prints the solution path by traversing from the goal node to the initial node, printing each state and the corresponding move count.

// The printState method prints a given state array.

// In the main method, an example initial state is defined, and an EightPuzzle instance is created with this initial state. The solve method is called to find the solution to the puzzle.


// The hashCode method calculates the hash code for a Node object based on the state array using Arrays.deepHashCode(state). Arrays.deepHashCode calculates the hash code of a multidimensional array by considering the individual elements of the array. This ensures that arrays with the same elements have the same hash code.

// The equals method checks for equality between two Node objects. It first performs reference equality checks: if the two objects are the same instance or if one of the objects is null or of a different class, it returns false. If the objects pass the reference equality checks, it compares the state arrays of the two objects using Arrays.deepEquals(state, other.state). This method checks for deep equality by comparing the individual elements of the arrays. If the state arrays of the two objects are deep-equal, it returns true, indicating that the objects are equal.
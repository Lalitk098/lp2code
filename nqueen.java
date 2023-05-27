// N Queens problem with 3 type of questions
// printing N - Queens - all ways
// total possible ways to sole N Queen problem
// print only 1 solution in a1_backtracking3.java


public class nqueen {

    public static boolean isSafe(char board[][], int row, int col){
        // checking for vertiaclly up
        for(int i=row-1; i>=0; i--){  // for going up
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        // checking for diagonally left up
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){    
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        // checking for diagonally right up
        for(int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++){    
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        return true;
    }

    public static void nQuuens(char board[][], int row){
        // base case
        if(row == board.length){

            // for printing N - Queens - all ways
            printBoard(board);
            System.out.println("-------------");
            return;

            // for total possible ways to sole N Queen problem
            // count++;
            // return;
        }

        // kaam or column loop for all possible results
        for(int j=0; j<board.length; j++){

            // checking that placing Queen is safe or not
            if(isSafe(board, row, j)){
                board[row][j] = 'Q';
                nQuuens(board, row+1);
    
                // backtracking
                board[row][j] = '_';
            }
        }
    }
    
    public static void printBoard(char board[][]){
        for(int i=0; i<board.length; i++){
            for(int j=0; j <board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int count=0;


    public static void main(String[] args) {
        int n = 5;      // for board 4x4
        char board[][] = new char[n][n];

        // initialize 
        for(int i=0; i<board.length; i++){
            for(int j=0; j <board.length; j++){
                board[i][j] = '_';
            }
        }

        nQuuens(board, 0);

        System.out.println("Totals way to solve N Queens of board " + n + " is : " + count);
    }
}


// Time complexity O(n!)
// recursion relation is T(n) = n*T(n-1) + isSafe()
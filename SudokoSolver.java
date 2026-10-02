public class SudokuSolver {

    public static void main(String[] args) {

        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Sudoku Before Solving:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    // Backtracking algorithm
    public static boolean solveSudoku(char[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    for (char num = '1'; num <= '9'; num++) {

                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // Check row, column and 3x3 box
    public static boolean isValid(
            char[][] board, int row, int col, char num) {

        for (int i = 0; i < 9; i++) {

            // Check row
            if (board[row][i] == num) {
                return false;
            }

            // Check column
            if (board[i][col] == num) {
                return false;
            }

            // Check 3x3 box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;

            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }

        return true;
    }

    // Display Sudoku
    public static void printBoard(char[][] board) {

        for (int row = 0; row < 9; row++) {

            if (row % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }

            for (int col = 0; col < 9; col++) {

                if (col % 3 == 0) {
                    System.out.print("| ");
                }

                System.out.print(board[row][col] + " ");
            }

            System.out.println("|");
        }

        System.out.println("+-------+-------+-------+");
    }
}

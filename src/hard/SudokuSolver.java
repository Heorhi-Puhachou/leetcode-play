package hard;

import java.util.Arrays;
import java.util.List;

public class SudokuSolver {
    public static void main(String... args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku(board);
        for (char[] line : board) {
            System.out.println(line);
        }
    }

    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     * <p>
     * A sudoku solution must satisfy all of the following rules:
     * <p>
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * <p>
     * The '.' character indicates empty cells.
     * <p>
     * Example 1:
     * <p>
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output:        [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     */


    public void solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char symbol = board[row][column];
                if (symbol == '.') {
                    List<Character> available = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
                    checkRow(available, board, row);
                    checkColumn(available, board, column);
                    checkSquare(available, board, row, column);
                    available = available.stream().filter(el -> el != '.').toList();
                    if (available.size() == 1) {
                        System.out.println(available);
                        System.out.println("" + row + "-" + column + ": " + board[row][column]);
                    }
                }
            }
        }
    }

    private void checkRow(List<Character> available, char[][] board, int rowIndex) {
        for (int column = 0; column < 9; column++) {
            char symbol = board[rowIndex][column];
            int availableIndex = available.indexOf(symbol);
            if (availableIndex >= 0) {
                available.set(availableIndex, '.');
            }
        }
    }

    private void checkColumn(List<Character> available, char[][] board, int columnIndex) {
        for (int row = 0; row < 9; row++) {
            char symbol = board[row][columnIndex];
            int availableIndex = available.indexOf(symbol);
            if (availableIndex >= 0) {
                available.set(availableIndex, '.');
            }
        }
    }

    private void checkSquare(List<Character> available, char[][] board, int rowIndex, int columnIndex) {
        int rowStart = (rowIndex / 3) * 3;
        int rowEnd = (rowIndex / 3 + 1) * 3;

        int columnStart = (columnIndex / 3) * 3;
        int columnEnd = (columnIndex / 3 + 1) * 3;

        for (int row = rowStart; row < rowEnd; row++) {
            for (int column = columnStart; column < columnEnd; column++) {
                char symbol = board[row][column];
                int availableIndex = available.indexOf(symbol);
                if (availableIndex >= 0) {
                    available.set(availableIndex, '.');
                }
            }
        }
    }
}

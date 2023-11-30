package hard;

import java.util.*;

public class SudokuSolver {
    public static void main(String... args) {
        Date startTime = new Date();
//        char[][] board = {
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] board = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        new SudokuSolver().solveSudoku(board);
        for (char[] line : board) {
            System.out.println(line);
        }
        Date endTime = new Date();
        System.out.println("\n\nms time: " + (endTime.getTime() - startTime.getTime()));
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

    private static final Set<Character> NUMBERS = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

    record VariantBoardValue(int row, int column, char value) {
    }

    static class VariantBoard {
        private boolean finished;
        private final char[][] board;

        public VariantBoard(char[][] originalBoard) {
            this.board = new char[9][9];
            for (int row = 0; row < 9; row++) {
                System.arraycopy(originalBoard[row], 0, board[row], 0, 9);
            }
        }

        public void markAsFinished() {
            this.finished = true;
        }

        public char[][] getBoard() {
            return board;
        }
    }

    public void solveSudoku(char[][] board) {
        VariantBoard result = tryFillBoard(new VariantBoard(board), null);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board[row][column] = result.getBoard()[row][column];
            }
        }
    }


    private List<VariantBoardValue> findMinRoundabout(char[][] board) {
        int minRoundAbout = 8;
        List<VariantBoardValue> variants = new ArrayList<>();
        Set<Character> lockedValues = new HashSet<>();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char symbol = board[row][column];
                if (symbol == '.') {
                    lockedValues.clear();
                    checkRow(lockedValues, board[row]);
                    checkColumn(lockedValues, board, column);
                    checkSquare(lockedValues, board, row, column);
                    if (9 - lockedValues.size() < minRoundAbout) {
                        minRoundAbout = 9 - lockedValues.size();
                        variants.clear();
                        for (Character number : NUMBERS) {
                            if (!lockedValues.contains(number)) {
                                variants.add(new VariantBoardValue(row, column, number));
                            }
                        }
                    }
                }
            }
        }
        return variants;
    }

    private VariantBoard tryFillBoard(VariantBoard variantBoard, VariantBoardValue additionalValue) {
        if (additionalValue != null) {
            variantBoard.getBoard()[additionalValue.row()][additionalValue.column()] = additionalValue.value();
        }
        char[][] board = variantBoard.getBoard();
        Set<Character> lockedValues = new HashSet<>();
        while (true) {
            boolean updated = false;
            int unfinished = 0;
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    char symbol = board[row][column];
                    if (symbol == '.') {
                        lockedValues.clear();
                        checkRow(lockedValues, board[row]);
                        checkColumn(lockedValues, board, column);
                        checkSquare(lockedValues, board, row, column);
                        if (lockedValues.size() == 9) {
                            return variantBoard;
                        }
                        if (lockedValues.size() == 8) {
                            board[row][column] = getUnlockedValue(lockedValues);
                            updated = true;
                        } else {
                            unfinished++;
                        }
                    }
                }
            }
            if (unfinished == 0) {
                variantBoard.markAsFinished();
                return variantBoard;
            }


            if (!updated) {
                // find min variants
                List<VariantBoardValue> variants = findMinRoundabout(board);

                for (VariantBoardValue variantBoardValue : variants) {
                    VariantBoard tempVariantBoard = tryFillBoard(new VariantBoard(board), variantBoardValue);
                    if (tempVariantBoard.finished) {
                        return tempVariantBoard;
                    }
                }
            }
        }
    }

    private void checkRow(Set<Character> lockedValues, char[] row) {
        for (int column = 0; column < 9; column++) {
            char symbol = row[column];
            if (symbol != '.') {
                lockedValues.add(symbol);
            }
        }
    }

    private void checkColumn(Set<Character> lockedValues, char[][] board, int columnIndex) {
        for (int row = 0; row < 9; row++) {
            char symbol = board[row][columnIndex];
            if (symbol != '.') {
                lockedValues.add(symbol);
            }
        }
    }

    private void checkSquare(Set<Character> lockedValues, char[][] board, int rowIndex, int columnIndex) {
        int rowStart = (rowIndex / 3) * 3;
        int rowEnd = (rowIndex / 3 + 1) * 3;

        int columnStart = (columnIndex / 3) * 3;
        int columnEnd = (columnIndex / 3 + 1) * 3;

        for (int row = rowStart; row < rowEnd; row++) {
            for (int column = columnStart; column < columnEnd; column++) {
                char symbol = board[row][column];
                if (symbol != '.') {
                    lockedValues.add(symbol);
                }
            }
        }
    }

    private char getUnlockedValue(Set<Character> lockedValues) {
        for (char number : NUMBERS) {
            if (!lockedValues.contains(number)) {
                return number;
            }
        }
        return '.';
    }
}

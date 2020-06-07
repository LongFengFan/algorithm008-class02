//37. 解数独
//编写一个程序，通过已填充的空格来解决数独问题。
//
//一个数独的解法需遵循如下规则：
//
//数字 1-9 在每一行只能出现一次。
//数字 1-9 在每一列只能出现一次。
//数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
//空白格用 '.' 表示。
//
//
//
//一个数独。
//
//
//
//答案被标成红色。
//
//Note:
//
//给定的数独序列只包含数字 1-9 和字符 '.' 。
//你可以假设给定的数独只有唯一解。
//给定数独永远是 9x9 形式的。
package Week_07;

import java.util.Arrays;

//https://leetcode-cn.com/problems/sudoku-solver/#/description
public class SolveSudoku {

    private static final int Max = 81;

    private IsValidSudoku isValidSudoku = new IsValidSudoku();


    public void solveSudoku(char[][] board) {
        solve(board);

    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c < '9'; c++) {
                        board[i][j] = c;
                        if (isValidSudoku.isValidSudoku(board)) {
                            if (solve(board)) {
                                return true;
                            }
//                            回溯
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }

            }
        }
        return true;
    }

    public void solveSudoku2(char[][] board) {
        dfs(board, 0);
    }

    private boolean dfs(char[][] board, int n) {
        if (n == Max) {
            return true;
        }
        int i = n / 9;
        int j = n % 9;
        if (board[i][j] != '.') {
            return dfs(board, n + 1);
        } else {
            for (char c = '1'; c < '9'; c++) {
                if(isValid(board, i, j, c)){
                    board[i][j] = c;
                    if (dfs(board, n + 1)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SolveSudoku().solve(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}

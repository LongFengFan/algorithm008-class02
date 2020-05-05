//51. N皇后
//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
//上图为 8 皇后问题的一种解法。
//
//给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
//
//每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//示例:
//
//输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
//
//
//提示：
//
//皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
package Week_03;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SolveNQueens {
    //    index
    private List<Integer> col = new ArrayList<>();

    //    行 + 列
    private  List<Integer> pie = new ArrayList<>();
    //    行 - 列
    private  List<Integer> na = new ArrayList<>();
    List<List<String>> lists  = new ArrayList<>();

//    回溯方法，
// 回溯框架讲解：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
//    可以配合老师的递归模板理解
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '.';
            }
        }

        helper(0, n, board);
//        helper2(0, n, board);
        return lists;
    }

    private void helper(int level, int n, char[][] board) {
        if (level == n) {
            lists.add(generateListFromBoard(board));
            return;
        }
//        检查方法1，需要配合三个缓存列，撇，捺，检查规则是，主对角线（由左上至右下的对角线）行 - 列为常数
//        次对角线(右上至左下的对角线) 行 + 列为常数
        for (int i = 0; i < n; i++) {
            if (col.contains(i) || pie.contains(level + i) || na.contains(level - i)) {
//                停止 go die
                continue;
            }
//            process
            col.add(i);
            pie.add(level + i);
            na.add(level - i);
            board[level][i] = 'Q';

//            recurse
            helper(level + 1, n, board);
//            reverse state 回溯
            col.remove(col.size() - 1);
            pie.remove(pie.size() - 1);
            na.remove(na.size() - 1);
            board[level][i] = '.';
        }
    }

    private void helper2(int level, int n, char[][] board) {
        if (level == n) {
            lists.add(generateListFromBoard(board));
            return;
        }
        for (int i = 0; i < n; i++) {
//            检查方法2
//            直接硬检,实际测试比引入缓存的时间复杂度更好。
            if (!isValid(board, level, i)){
                continue;
            }
//            process
            board[level][i] = 'Q';

//            recurse
            helper(level + 1, n, board);
//            reverse state 回溯
            board[level][i] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    private List<String> generateListFromBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] chars : board) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                sb.append(chars[j]);
            }
            list.add(sb.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new SolveNQueens().solveNQueens(4);
        System.out.println(lists);
    }
}

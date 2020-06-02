//212. 单词搜索 II
//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
//单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
// 同一个单元格内的字母在一个单词中不允许被重复使用。
//
//示例:
//
//输入:
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"]
package Week_07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private Set<String> set;

    private Trie trie;

    private char[][] board;


    public List<String> findWords(char[][] board, String[] words) {
        trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        set = new HashSet<>();
        this.board = board;
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (!trie.contaisKey(c)) {
                    dfs(new StringBuilder(), i, j, visited);
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(StringBuilder sb, int row, int col, boolean[][] visited) {
//        terminator
        if (row > board.length - 1
                || row < 0
                || col > board[0].length - 1
                || col < 0
                || visited[row][col]) return;
//        logic
        sb.append(board[row][col]);
        String currString = sb.toString();
        Trie node = this.trie.searchPrefix(currString);
        visited[row][col] = true;
        if (node != null) {
            if (node.isEnd()) set.add(currString);
//            drill down
//            上下左右
            for (int i = 0; i < 4; i++) {
                dfs(sb, row + dx[i], col + dy[i], visited);
            }
        }
//        reverse state
        sb.deleteCharAt(sb.length() - 1);
        visited[row][col] = false;
    }
}

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
import java.util.List;
import java.util.Set;

public class SolveNQueens {
    //    index
    private Set<Integer> col = new HashSet<>();

    //    行 + 列
    private Set<Integer> pie = new HashSet<>();
    //    行 - 列
    private Set<Integer> na = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        helper(0, n, list, lists);
        return lists;
    }

    private void helper(int level, int n, List<String> list, List<List<String>> lists) {
        if (level == n) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col.contains(i) || pie.contains(level + i) || na.contains(level - i)) {
//                停止 go die
                continue;
            }
//            process
            col.add(i);
            pie.add(level + i);
            na.add(level - i);
            list.add(generateString(i, n));

//            recurse
            helper(level + 1, n, list, lists);
//            reverse state
            col.remove(col.size() - 1);
            pie.remove(pie.size() - 1);
            na.remove(na.size() - 1);
//            list.remove(list.size() - 1);
        }
    }

    private String generateString(int i, int n) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            if (j == i) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<List<String>> lists = new SolveNQueens().solveNQueens(4);
        System.out.println(lists);
    }
}

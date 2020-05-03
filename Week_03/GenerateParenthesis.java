//22. 括号生成
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
//示例：
//
//输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
package Week_03;


import java.util.List;

public class GenerateParenthesis {
    //    分析
//    n==1 ()
//    n==2 (()) ()()
    public List<String> generateParenthesis(int n) {
        String line = "";
        recurse(0, n, line);
        return null;
    }

    private void recurse(int level, int max, String line) {
//        terminator
        if (level == max) {
//            process result
            System.out.println(line);
            return;
        }

//        process current logic
        String line1 = line + "(";
        String line2 = line + ")";
//        drill down
        recurse(level + 1, max, line1);
        recurse(level + 1, max, line2);
//        restore status

    }

    public static void main(String[] args) {
        new GenerateParenthesis().generateParenthesis(3);
    }
}

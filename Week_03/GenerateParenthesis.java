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


import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    List<String> list = new ArrayList<>();

    //    最原始的应该暴力穷举，再删除不合法的，不过我们可以在穷举的时候就确定合法性
//    可以减少很多非法元素
    public List<String> generateParenthesis(int n) {

        String line = "";
        int left = 0;
        int right = 0;
        recurse(left, right, n, line);
        return list;
    }

    private void recurse(int left, int right, int n, String line) {
        //        terminator
        if (left == n && right == n) {
            list.add(line);
            return;
        }
//        process

//        drill down
//        剪枝1，左括号不满n个，可以加左括号
        if (left < n) {
            recurse(left + 1, right, n, line + "(");
        }
        //        剪枝2，右括号小于左括号，可以加右括号
        if (right < left) {
            recurse(left, right + 1, n, line + ")");
        }
//        restore status
    }

    public static void main(String[] args) {
        new GenerateParenthesis().generateParenthesis(3);
    }
}

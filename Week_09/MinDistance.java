//72. 编辑距离
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
//你可以对一个单词进行如下三种操作：
//
//插入一个字符
//删除一个字符
//替换一个字符
//
//
//示例 1：
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//示例 2：
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
package Week_09;

public class MinDistance {
    //    dp[i][j]  i代表word1 0-i个字符 和 word2 0 - j个字符的最大边界距离
//    当... x  ..x，即最后一个字符相同，那么dp[i][j] = dp[i - 1][j - 1]
//                                                                 abcd  abce         abcd abc           abc abcd
//                                                                                    sma ijsm
//    当... x  ..y，即最后一个字符不相同，那么dp[i][j] = Math.min(dp[i - 1][j - 1] + 1,dp[i - 1][j] + 1，dp[i][j - 1] + 1])
//    初始化base 当i ==0 dp[0][j] = j
//    初始化base 当j ==0 dp[i][0] = i
//  e   5
//  s   4
//  r   3
//  o   2
//  h   1
//  #   0 1 2 3
//      # r o s
//


    
}

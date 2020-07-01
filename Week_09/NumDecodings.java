//91. 解码方法
//一条包含字母 A-Z 的消息通过以下方式进行了编码：
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//给定一个只包含数字的非空字符串，请计算解码方法的总数。
//
//示例 1:
//
//输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
//示例 2:
//
//输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
package Week_09;
public class NumDecodings {
//    243108
//    dp[0] = 1
//    dp[1] = 0;
//    dp[2] = dp[2 - 1] + dp[i - 2]
//    dp[3] = dp(3 - 1)

//    从右往左
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n];
        dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;
        if(n == 1) return dp[0];
        if(s.charAt(n - 2) == '0'){
            dp[n - 2] = 0;
        }else {
            int val = (s.charAt(n - 2) - '0') * 10 + s.charAt(n - 1) - '0';
            dp[n - 2] = val > 26  ? dp[n - 1] : dp[n -1] + 1;
        }
        for (int i = s.length() - 3; i >= 0; i--) {
            if(s.charAt(i) == '0') {
                dp[i] = 0;
            }else {
                dp[i] = (s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0' > 26  ? dp[i + 1] : dp[i + 1] + dp[i + 2];
            }
        }
        return dp[0];
    }



    public static void main(String[] args) {
        int i = new NumDecodings().numDecodings("226");
        System.out.println(i);
    }
}

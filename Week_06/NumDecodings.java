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
package Week_06;

public class NumDecodings {
    public int numDecodings(String s) {
//        分治子问题
//        从后往前，会少很多分支判断
//当最后一位为0，dp[i] = 0 ,否则为1

//        状态数组

//        dp方程
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        if (s.charAt(len - 1) == '0') {
            dp[len - 1] = 0;
        } else {
            dp[len - 1] = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int i = new NumDecodings().numDecodings("10012");
        System.out.println(i);
    }
}

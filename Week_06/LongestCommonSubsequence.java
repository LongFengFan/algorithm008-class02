//1143. 最长公共子序列
//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
//
//一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
//
//若这两个字符串没有公共子序列，则返回 0。
//
//
//
//示例 1:
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace"，它的长度为 3。
//示例 2:
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
//示例 3:
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
//
//
//提示:
//
//1 <= text1.length <= 1000
//1 <= text2.length <= 1000
//输入的字符串只含有小写英文字符。
package Week_06;

import org.springframework.util.StringUtils;

public class LongestCommonSubsequence {
    //    暴力法
    public int longestCommonSubsequence(String text1, String text2) {
//        选择text2 为长度较小的那个
        if (text1.length() < text2.length()) {
            String tmp = text2;
            text2 = text1;
            text1 = tmp;
        }
//        穷举text2的子序列
        return childLength(text2, text1, 0, "", 0);
    }

    private int childLength(String text, String text2, int n, String curr, int max) {
        if (n == text.length()) {
            if (isValid(curr, text2)) {
                return Math.max(curr.length(), max);
            }
            return 0;
        }
        int i = childLength(text, text2, n + 1, curr.concat(Character.toString(text.charAt(n))), max);
        int i1 = childLength(text, text2, n + 1, curr, max);
        return Math.max(i, i1);

    }

    private boolean isValid(String curr, String text) {
        int count = 0;
        for (int i = 0; i < curr.length(); i++) {
            for (int p = 0; p < text.length(); p++) {
                if (curr.charAt(i) == text.charAt(p)) {
                    ++count;
                    ++p;
                    break;
                }
            }
        }
        return count == curr.length();
    }

    public static void main(String[] args) {
        String text1 = "abc";
        String text2 = "aaa";
        int i = new LongestCommonSubsequence().longestCommonSubsequence2(text1, text2);
        System.out.println(i);
    }


    //    动态规划
    public int longestCommonSubsequence2(String text1, String text2) {
        int length1 = text1.length() + 1;
        int length2 = text2.length() + 1;
        int[][] arr = new int[length1][length2];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    arr[i + 1][j + 1] = arr[i][j] + 1;
                } else {
                    arr[i + 1][j + 1] = Math.max(arr[i][j + 1], arr[i + 1][j]);
                }
            }
        }
        return arr[length1 - 1][length2 - 1];
    }
}


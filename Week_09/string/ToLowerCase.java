//709. 转换成小写字母
//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
//
//
//
//示例 1：
//
//输入: "Hello"
//输出: "hello"
//示例 2：
//
//输入: "here"
//输出: "here"
//示例 3：
//
//输入: "LOVELY"
//输出: "lovely"
package Week_09.string;

public class ToLowerCase {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                sb.append((char) (str.charAt(i) + 32));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public int lengthOfLastWord(String s) {
        int max = 0;
        boolean flag = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (flag) {
                    return max;
                }
            } else {
                flag = true;
                max++;
            }
        }
        return max;
    }

    public int numJewelsInStones(String J, String S) {
        int max = 0;
        while (S.contains(J)) {
            S = S.replaceFirst(J, "");
            max++;
        }
        return max;
    }
}

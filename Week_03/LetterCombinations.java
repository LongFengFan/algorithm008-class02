//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
//给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//示例:
//
//输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package Week_03;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private static final Map<Character, String> map = new HashMap<>();

    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) return list;
        helper("", 0, list, digits);
        return list;

    }

    private void helper(String line, int index, List<String> list, String digits) {
//        terminator
        if (index >= digits.length()) {
            list.add(line);
            return;
        }

//        process
        String s = map.get(digits.charAt(index));
        for (int i = 0; i < s.length(); i++) {
            String newLine = line + s.charAt(i);
//        recurse
            helper(newLine, index + 1, list, digits);
        }
//        reverse
    }

    public static void main(String[] args) {
        List<String> list = new LetterCombinations().letterCombinations("23");
        System.out.println(list);
    }
}

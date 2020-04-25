//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
//示例 1:
//
//输入: s = "anagram", t = "nagaram"
//输出: true
//示例 2:
//
//输入: s = "rat", t = "car"
//输出: false
//说明:
//你可以假设字符串只包含小写字母。
//
//进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-anagram
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package Week_02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsAnagram {
//    排序比较法
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.equals(t)) return true;

        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        char[] chars2 = t.toCharArray();
        Arrays.sort(chars2);

        for (int i = 0; i < s.length(); i++) {
            if(chars[i] != chars2[i]) return false;
        }
        return true;
    }

//    map比较法
    public boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()) return false;
        if(s.equals(t)) return true;
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = map.containsKey(c) ? map.get(c) + 1 : 1;
            map.put(c, count);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = map2.containsKey(c) ? map2.get(c) + 1 : 1;
            map2.put(c, count);
        }

        return map.equals(map2);
    }
}

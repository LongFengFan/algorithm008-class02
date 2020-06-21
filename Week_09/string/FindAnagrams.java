//438. 找到字符串中所有字母异位词
//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
//
//字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
//
//说明：
//
//字母异位词指字母相同，但排列不同的字符串。
//不考虑答案输出的顺序。
//示例 1:
//
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 示例 2:
//
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
package Week_09.string;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FindAnagrams {

    //    利用map
    public List<Integer> findAnagrams(String s, String p) {
        if (p == null || p.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList<Integer> array = new ArrayList<>();
        char[] pC = p.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : pC) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> map2 = new HashMap<>();
        char[] sC = s.toCharArray();
        for (int i = 0; i < sC.length; i++) {
            int windowFirstIndex = i + 1 - pC.length;
            map2.put(sC[i], map2.getOrDefault(sC[i], 0) + 1);
            if ((i + 1) >= pC.length) {
                if (map.equals(map2)) {
                    array.add(windowFirstIndex);
                }
                Integer windowFirstMapV = map2.get(sC[windowFirstIndex]);
                if (windowFirstMapV == 1) {
                    map2.remove(sC[windowFirstIndex]);
                } else {
                    map2.put(sC[windowFirstIndex], windowFirstMapV - 1);
                }
            }
        }
        return array;
    }


    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("cbaebabacd", "abc"));
    }

    //    双指针
    public List<Integer> findAnagrams3(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        ArrayList<Integer> res = new ArrayList<>(); // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c)))
                    valid++;
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= p.length()) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size())
                    res.add(left);
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(c, window.getOrDefault(c, 0) - 1);
                }
            }
        }
        return res;
    }


    //    稍微暴力的排序比较法 超时
    public List<Integer> findAnagrams2(String s, String p) {
        if (p == null || p.length() == 0) {
            return Collections.emptyList();
        }
        ArrayList<Integer> array = new ArrayList<>();
        LinkedList<Character> queue = new LinkedList<>();
        char[] pC = p.toCharArray();
        Arrays.sort(pC);
        char[] sC = s.toCharArray();
        for (int i = 0; i < sC.length; i++) {
            queue.addLast(sC[i]);
            if (queue.size() == pC.length) {
                StringBuilder sb = new StringBuilder();
                for (Character character : queue) {
                    sb.append(character);
                }
                if (isAnagram(sb.toString(), pC)) {
                    array.add(i + 1 - pC.length);
                }
                queue.poll();
            }
        }
        return array;
    }

    //    排序比较法
    private boolean isAnagram(String s, char[] chars2) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != chars2[i]) return false;
        }
        return true;
    }
}

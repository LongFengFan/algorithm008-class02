//49. 字母异位词分组
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
//示例:
//
//输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//说明：
//
//所有输入均为小写字母。
//不考虑答案输出的顺序。
package Week_02;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
//    排序 + StringBuilder作为key + map
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = getString(chars);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                map.put(key, strings);
            }

        }
        return new ArrayList<>(map.values());
    }

    String getString(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }
}

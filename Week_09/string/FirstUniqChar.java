//387. 字符串中的第一个唯一字符
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
//
//
//示例：
//
//s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
//
//
//提示：你可以假定该字符串只包含小写字母。
package Week_09.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], 2);
            } else {
                map.put(chars[i], 1);
            }
        }
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if(e.getValue() == 1){
                return s.indexOf(e.getKey());
            }
        }
        return -1;
    }
}

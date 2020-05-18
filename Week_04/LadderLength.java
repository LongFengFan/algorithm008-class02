//127. 单词接龙
//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
//每次转换只能改变一个字母。
//转换过程中的中间单词必须是字典中的单词。
//说明:
//
//如果不存在这样的转换序列，返回 0。
//所有单词具有相同的长度。
//所有单词只由小写字母组成。
//字典中不存在重复的单词。
//你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
//示例 1:
//
//输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
//示例 2:
//
//输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。
package Week_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LadderLength {
//    深度优先，超时
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        int[] least = {0};
        dfs(beginWord, endWord, 0, wordList, least);
        return least[0];
    }

    private void dfs(String beginWord, String endWord, int i, List<String> wordList, int[] least) {
        if (beginWord.equals(endWord)) {
            least[0] = least[0] == 0 ? i + 1 : Math.min(least[0], i + 1);
        }
        for (String s : wordList) {
            if (compareTwoS(beginWord, s)) {
                ArrayList<String> list = new ArrayList<>(wordList);
                list.remove(s);
                dfs(s, endWord, i + 1, list, least);
            }
        }
    }

    boolean compareTwoS(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr",
                "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra",
                "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn",
                "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb",
                "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di",
                "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er",
                "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni",
                "mr", "pa", "he", "lr", "sq", "ye");
        int i = new LadderLength().ladderLength("qa", "sq", list);
        System.out.println(i);
    }

//    广度优先
public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
    if(!wordList.contains(endWord)){
        return 0;
    }
    HashSet<String> visited = new HashSet<>();
    LinkedList<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    visited.add(beginWord);
    while (!queue.isEmpty()){
        String poll = queue.poll();
        for (String s : wordList) {
            if(visited.contains(s)) continue;
            if(!compareTwoS(beginWord,s)) continue;

        }
    }
}
}

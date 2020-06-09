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
//https://leetcode-cn.com/problems/word-ladder/
package Week_07;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(beginWord);
        visited.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) return count;
                for (String s : wordList) {
                    if (!visited.contains(s)) {
                        if(compare(poll, s)){
                            queue.addLast(s);
                            visited.add(s);
                        }
                    }
                }
            }

        }
        return 0;
    }

    private boolean compare(String poll, String s) {
        int count = 0;
        for (int i = 0; i < poll.length(); i++) {
            if(poll.charAt(i) != s.charAt(i)){
                count++;
                if(count > 1) return false;
            }
        }
        return count == 1;
    }
}

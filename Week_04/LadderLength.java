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
        if (!wordList.contains(endWord)) {
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
        if (s1.length() != s2.length()) return false;
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 1) return false;
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
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
//        初始化第一层
        queue.addLast(beginWord);
//        初始化visited
        visited.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
//            进入一层count++
            count++;
//            不能再for循环中直接使用i < queue.size，因为queue的size在不断变化
            int size = queue.size();
//            针对该层遍历
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (String s : wordList) {
                    if (visited.contains(s)) continue;
                    if (!compareTwoS(poll, s)) continue;
                    if (s.equals(endWord)) {
                        return count;
                    }
                    visited.add(s);
                    queue.addLast(s);
                }
            }

        }
        return 0;
    }

    //    优化广度优先的visited标记
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        boolean[] visited = new boolean[wordList.size()];
        LinkedList<String> queue = new LinkedList<>();
//        初始化第一层
        queue.addLast(beginWord);
//        初始化visited
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }
        int count = 1;
        while (!queue.isEmpty()) {
//            进入一层count++
            count++;
//            不能再for循环中直接使用i < queue.size，因为queue的size在不断变化
            int size = queue.size();
//            针对该层遍历
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String s = wordList.get(j);
                    if (visited[j]) continue;
                    if (!compareTwoS(poll, s)) continue;
                    if (s.equals(endWord)) {
                        return count;
                    }
                    visited[j] = true;
                    queue.addLast(s);
                }
            }

        }
        return 0;
    }

    //双向BFS
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        int endWordIndex = wordList.indexOf(endWord);
        if (endWordIndex == -1) return 0;
        wordList.add(beginWord);
        //从begin_word搜索
        boolean[] visited1 = new boolean[wordList.size()];
        LinkedList<String> queue1 = new LinkedList<>();
        queue1.addLast(beginWord);
        visited1[wordList.size() - 1] = true;
//    从end_word搜索
        boolean[] visited2 = new boolean[wordList.size()];
        LinkedList<String> queue2 = new LinkedList<>();
        queue2.addLast(endWord);
        visited2[endWordIndex] = true;
        int depth1 = 0;
        int depth2 = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
//            进入一层count++
            depth1++;
            int size1 = queue1.size();
//            针对该层遍历
            for (int i = 0; i < size1; i++) {
                String poll = queue1.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String s = wordList.get(j);
                    if (visited1[j]) continue;
                    if (!compareTwoS(poll, s)) continue;
//                搜索碰头
                    if (visited2[j]) {
                        return depth1 + depth2 + 1;
                    }
                    visited1[j] = true;
                    queue1.addLast(s);
                }
            }
            depth2++;
            int size2 = queue2.size();
//            针对该层遍历
            for (int i = 0; i < size2; i++) {
                String poll = queue2.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String s = wordList.get(j);
                    if (visited2[j]) continue;
                    if (!compareTwoS(poll, s)) continue;
//                搜索碰头
                    if (visited1[j]) {
                        return depth1 + depth2 + 1;
                    }
                    visited2[j] = true;
                    queue2.addLast(s);
                }
            }
        }
        return 0;
    }

    //    接下来开始对双向BFS进行优化。
//从AB两个方向的中，选择当前节点更少的队列，进行层序遍历。
    public int ladderLength5(String beginWord, String endWord, List<String> wordList) {
        int endWordIndex = wordList.indexOf(endWord);
        if (endWordIndex == -1) return 0;
        wordList.add(beginWord);
        //从begin_word搜索
        boolean[] visited1 = new boolean[wordList.size()];
        LinkedList<String> queue1 = new LinkedList<>();
        queue1.addLast(beginWord);
        visited1[wordList.size() - 1] = true;
//    从end_word搜索
        boolean[] visited2 = new boolean[wordList.size()];
        LinkedList<String> queue2 = new LinkedList<>();
        queue2.addLast(endWord);
        visited2[endWordIndex] = true;
        int depth = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
//            进入一层count++
            depth++;
            if (queue1.size() > queue2.size()) {
                LinkedList<String> tmpQueue = queue1;
                queue1 = queue2;
                queue2 = tmpQueue;
                boolean[] temVisited = visited1;
                visited1 = visited2;
                visited2 = temVisited;
            }
            int size1 = queue1.size();
//            针对该层遍历
            for (int i = 0; i < size1; i++) {
                String poll = queue1.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String s = wordList.get(j);
                    if (visited1[j]) continue;
                    if (!compareTwoS(poll, s)) continue;
//                搜索碰头
                    if (visited2[j]) {
                        return depth + 1;
                    }
                    visited1[j] = true;
                    queue1.addLast(s);
                }
            }
        }
        return 0;

    }
}

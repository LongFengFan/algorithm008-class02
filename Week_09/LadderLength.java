package Week_09;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.addLast(beginWord);
        visited.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    if (visited.contains(wordList.get(j))) continue;
                    if (!compare(poll, wordList.get(j))) continue;
                    if(wordList.get(j).equals(endWord)) return count;
                    queue.addLast(wordList.get(j));
                    visited.add(wordList.get(j));
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

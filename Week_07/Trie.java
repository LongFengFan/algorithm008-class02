/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package Week_07;

public class Trie {
    private boolean isEnd;
    private Trie[] links;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        links = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.links[n] == null) curr.links[n] = new Trie();
            curr = curr.links[n];
        }
        curr.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    public boolean contaisKey(char c) {
       return links[c - 'a'] == null;
    }

    public Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.links[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("to");
        trie.insert("tea");
        trie.insert("teach");
        boolean tea = trie.search("tea");
        System.out.println(tea);
        boolean teach = trie.search("teach");
        System.out.println(teach);
        Trie trie1 = trie.searchPrefix("tea");
        System.out.println(trie1);

    }
}

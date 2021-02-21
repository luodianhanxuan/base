package com.wangjg.practice;

import java.util.Objects;

/**
 * @author wangjg
 * 2020/7/21
 */
public class ArrayTrie {

    private Character v;
    private ArrayTrie[] children;
    private boolean endOfWord;

    public ArrayTrie() {
        this(null);
    }

    public ArrayTrie(Character c) {
        children = new ArrayTrie[26];
        endOfWord = false;
        v = c;
    }

    public void insert(String word) {
        assertNotEmpty(word);

        ArrayTrie tmp = this;
        for (int i = 0; i < word.length(); i++) {
            char theLetter = word.charAt(i);
            int pos = theLetter - 'a';
            if (tmp.children[pos] == null) {
                tmp.children[pos] = new ArrayTrie(theLetter);
            }
            tmp = tmp.children[pos];
        }
        tmp.endOfWord = true;
    }

    public boolean search(String word) {
        assertNotEmpty(word);

        ArrayTrie tmp = this;
        for (int i = 0; i < word.length(); i++) {
            char theLetter = word.charAt(i);
            int pos = theLetter - 'a';
            if (tmp.children[pos] == null) {
                return false;
            }
            tmp = tmp.children[pos];
        }

        return tmp.endOfWord;
    }

    public boolean startWith(String word) {
        assertNotEmpty(word);

        ArrayTrie tmp = this;
        for (int i = 0; i < word.length(); i++) {
            char theLetter = word.charAt(i);
            int pos = theLetter - 'a';
            if (tmp.children[pos] == null) {
                return false;
            }
            tmp = tmp.children[pos];
        }
        return true;
    }

    // 无锁、缓存、锁细


    private void assertNotEmpty(String word) {
        if (word == null || Objects.equals("", word)) {
            throw new IllegalArgumentException("the word to insert cannot be empty");
        }
    }

    public static void main(String[] args) {
        ArrayTrie trie = new ArrayTrie();
        trie.insert("helloworld");
        System.out.println(trie.search("helloworld"));
        System.out.println(trie.startWith("hello"));
        System.out.println(trie);
    }

}

package com.wangjg.algorithm.trie.arrayimpl;

/**
 * @author wangjg
 * 2020/3/17
 */
public class Trie {

    /**
     * 标识是否到此是一个完整的单词
     */
    private boolean endOfWord;

    /**
     * 直接儿子结点
     */
    private Trie[] children;

    /**
     * 代表的字符
     */
    private Character c;

    public Trie() {
        this(null);
    }

    public Trie(Character c) {
        this.c = c;
        this.endOfWord = false;
        // 26 个英文单词
        this.children = new Trie[26];
    }

    /**
     * 向字典树插入单词
     */
    public void insert(String word) {
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null) {
                p.children[index] = new Trie(letter);
            }
            p = p.children[index];
        }

        p.endOfWord = true;
    }

    /**
     * 查看单词是否在字典树中存在
     */
    public boolean search(String word) {
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = letter - 'a';
            if (p.children[index] == null) {
                return false;
            }
        }
        return p.endOfWord;
    }

    /**
     * 查看字典树中是否存在 word 开头的单词
     */
    public boolean startsWith(String word) {
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = letter - 'a';
            if (p.children[index] == null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        boolean res1 = obj.search("apple");
        boolean res2 = obj.search("app");
        boolean res3 = obj.startsWith("app");
        obj.insert("app");
        boolean res4 = obj.search("app");

    }
}

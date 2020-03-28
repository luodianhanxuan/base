package com.wangjg.algorithm.trie.mapimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjg
 * 2020/3/19
 */
@SuppressWarnings("DuplicatedCode")
public class Trie {

    /**
     * 标识是否到此是一个完整的单词
     */
    private boolean endOfWord;

    /**
     * 直接儿子结点
     */
    private Map<Character, Trie> children;

    /**
     * 代表的字符
     */
    private Character letter;

    public Trie() {
        this(null);
    }

    public Trie(Character letter) {
        this.letter = letter;
        this.endOfWord = false;
        this.children = new HashMap<>();
    }


    /**
     * 向字典树插入单词
     */
    public void insert(String word) {
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            p = p.children.computeIfAbsent(letter, key -> new Trie(letter));
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
            if (!p.children.containsKey(letter)) {
                return false;
            }
            p = p.children.get(letter);
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
            if (!p.children.containsKey(letter)) {
                return false;
            }
            p = p.children.get(letter);
        }
        return true;
    }

    /**
     * 查看字典树中以 prefix 开头的单词
     */
    public List<String> matchPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        if (prefix == null || prefix.equals("")) {
            return res;
        }

        char letter;
        Trie p = this;
        for (int i = 0; i < prefix.length(); i++) {
            letter = prefix.charAt(i);
            if (!p.children.containsKey(letter)) {
                return res;
            }
            p = p.children.get(letter);
        }

        matchPrefixHelper(p, prefix, res);
        return res;
    }

    private void matchPrefixHelper(Trie root, String s, List<String> container) {
        if (root.endOfWord) {
            container.add(s);
        }

        for (Map.Entry<Character, Trie> entry : root.children.entrySet()) {
            matchPrefixHelper(entry.getValue(), s + entry.getValue().letter, container);
        }
    }

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("你好");
        trie.insert("你");
        System.out.println(trie.search("你好"));
        System.out.println(trie.startsWith("你好"));
        trie.insert("你好吗");
        trie.insert("你真棒");
        trie.insert("你好坏");
        trie.insert("你真坏");
        System.out.println(trie.search("你好"));
        System.out.println(trie.startsWith("你好"));
        System.out.println(trie.search("你好吗"));
        System.out.println(trie.startsWith("你好吗"));

        System.out.println(trie.matchPrefix("你"));
    }
}

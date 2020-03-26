package com.wangjg.algorithm.trie.mapimpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjg
 * 2020/3/19
 */
public class Trie {

    /**
     * 标识是否到此是一个完整的单词
     */
    private boolean endOfWord;

    /**
     * 直接儿子结点
     */
    private Map<String, Trie> children;

    /**
     * 代表的字符
     */
    private String val;

    public Trie() {
        this(null);
    }

    public Trie(String c) {
        this.val = c;
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
            p = p.children.computeIfAbsent(String.valueOf(letter), key -> new Trie(String.valueOf(letter)));
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
           if (!p.children.containsKey(String.valueOf(letter))) {
               return false;
           }
           p = p.children.get(String.valueOf(letter));
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
            if (!p.children.containsKey(String.valueOf(letter))) {
                return false;
            }
            p = p.children.get(String.valueOf(letter));
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
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);

        String str = "12314";
    }
}

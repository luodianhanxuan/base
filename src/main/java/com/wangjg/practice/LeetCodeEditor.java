package com.wangjg.practice;

import java.util.Objects;

/**
 * @author wangjg
 * 2020/8/21
 */
public class LeetCodeEditor {

    public boolean judgeCircle(String moves) {
        int helper = ~0;
        char[] chars = moves.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char thisMove = chars[i];
            if ('U' == thisMove) {
                helper >>= 2;
            } else if (Objects.equals('D', thisMove)) {
                helper <<= 2;
            } else if (Objects.equals('L', thisMove)) {
                helper >>= 1;
            } else {
                helper <<= 1;
            }
        }
        return helper == ~0;
    }

    public static void main(String[] args) {
        LeetCodeEditor editor = new LeetCodeEditor();
        System.out.println(editor.judgeCircle("DU"));
    }
}

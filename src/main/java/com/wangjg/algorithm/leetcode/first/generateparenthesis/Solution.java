package com.wangjg.algorithm.leetcode.first.generateparenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjg
 * 2020/2/16
 */
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> container = new ArrayList<>();
        doGenerateParenthesis(0, 0, n, "", container);
        return container;
    }

    private void doGenerateParenthesis(int left, int right, int n, String s, List<String> container) {
        if (s.length() == n * 2) {
            container.add(s);
            return;
        }
        if (left < n) {
            doGenerateParenthesis(left + 1, right, n, s + "(", container);
        }
        if (right < left) {
            doGenerateParenthesis(left, right + 1, n, s + ")", container);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(4));
    }
}

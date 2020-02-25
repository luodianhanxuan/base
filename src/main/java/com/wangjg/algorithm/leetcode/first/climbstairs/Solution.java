package com.wangjg.algorithm.leetcode.first.climbstairs;

/**
 * @author wangjg
 * 2020/2/16
 * <p>
 * 懵逼的时候：
 * 暴力？基本情况
 * <p>
 * 找最近重复子问题
 * <p>
 * if else ,
 * for while,
 * recursion
 * <p>
 * 1 : 1
 * 2 : 2
 * 3 : f(1) + f(2)
 * 4 : f(2) + f(3)
 * <p>
 * f(n) = f(n - 1) + f(n - 2)     Fabonacci
 */
public class Solution {

    public int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }
        int f1 = 1, f2 = 2, f3 = 3;

        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }

        return f3;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(2));
    }
}

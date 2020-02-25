package com.wangjg.algorithm.leetcode.first.maxarea;

/**
 * @author wangjg
 * 2020/2/16
 */
public class Solution {
    /**
     * 枚举：求出所有的面积，找到面积（ (j - i) * minHeight ）最大值
     */
    public int maxArea0(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length - 1; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int area = (j - i) * Math.min(heights[i], heights[j]);
                max = Math.max(area, max);
            }
        }
        return max;
    }

    /**
     * 从最大宽度开始向内收敛，左右夹逼
     */
    public int maxArea1(int[] heights) {
        int max = 0;
        for (int i = 0, j = heights.length - 1; j > i; ) {
            int minHeight = heights[i] < heights[j] ? heights[i++] : heights[j--];
            // 上一步更新 i或者j （i++ / j--）的值，会导致当前的距离差 -1，所以，此时应两者相差 +1
            int area = (j - i + 1) * minHeight;
            max = Math.max(area, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea1(heights));
    }
}

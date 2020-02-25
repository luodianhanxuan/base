package com.wangjg.algorithm.leetcode.first.twosum;

import java.util.Arrays;

/**
 * @author wangjg
 * 2020/2/16
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] != target) {
                    continue;
                }
                return new int[]{i, j};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = new int[]{0, -1, 3, 2, -2};
        System.out.println(Arrays.toString(solution.twoSum(ints, 2)));
    }
}

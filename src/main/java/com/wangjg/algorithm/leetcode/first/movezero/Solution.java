package com.wangjg.algorithm.leetcode.first.movezero;

import java.util.Arrays;

/**
 * @author wangjg
 * 2020/2/16
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        // 记录数组中当前非零元素的位置
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            // 将当前非零元素填充到 j 位置，如果需要，则在当前位置填充 0
            nums[j] = nums[i];
            if (i != j) {
                // 说明在当前非零元素之前出现了 0 ，而当前非零元素被填充到了 j 位置，此位置应补充 0 (相当于将当前非零元素与0进行互换)
                nums[i] = 0;
            }
            j++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = new int[]{1, 0, 0, 2, 2};
        solution.moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }
}

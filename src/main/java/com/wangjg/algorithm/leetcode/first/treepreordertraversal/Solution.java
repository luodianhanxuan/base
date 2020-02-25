package com.wangjg.algorithm.leetcode.first.treepreordertraversal;

import com.wangjg.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangjg
 * 2020/2/21
 */
public class Solution {

    /**
     * 迭代方式
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode tmp = root;
        while (tmp != null || !stack.isEmpty()) {
            // 所有的左子树
            while (tmp != null) {
                res.add(tmp.val);
                stack.push(tmp);
                tmp = tmp.left;
            }
            tmp = stack.pop().right;
        }
        return res;
    }

    /**
     * 递归方式
     */
    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        doPreorderTraversal0(res, root);
        return res;
    }

    private void doPreorderTraversal0(List<Integer> res, TreeNode root) {
        if (root != null) {
            res.add(root.val);
            this.doPreorderTraversal0(res, root.left);
            this.doPreorderTraversal0(res, root.right);
        }
    }


    public static void main(String[] args) {

    }
}

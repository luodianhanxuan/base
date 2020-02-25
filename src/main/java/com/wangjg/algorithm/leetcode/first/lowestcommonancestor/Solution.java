package com.wangjg.algorithm.leetcode.first.lowestcommonancestor;

import com.wangjg.algorithm.tree.TreeNode;

/**
 * @author wangjg
 * 2020/2/16
 */
public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p.val == root.val || q.val == root.val) {
            return root;
        }
        //看看左子树中是否有 p 或 q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //看看右子树中是否有 p 或 q
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 左右结点都找到了，那么 结果是 root, 否则的话，就是结果就是左右结点
        if (left != null && right != null) {
            return root;
        }
        // 左结点没找到，那么就是右结点，右结点没找到，那么就是左结点
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(5);
        n1.left = n2;
        TreeNode n3 = new TreeNode(1);
        n1.right = n3;
        n2.left = new TreeNode(6);
        TreeNode n5 = new TreeNode(2);
        n2.right = n5;
        n3.left = new TreeNode(0);
        n3.right = new TreeNode(8);
        n5.left = new TreeNode(7);
        TreeNode n9 = new TreeNode(4);
        n5.right = n9;

        Solution solution = new Solution();
        System.out.println(solution.lowestCommonAncestor(n1, n2, n9).val);
    }
}

package com.wangjg.algorithm.tree;

import java.util.List;

/**
 * @author wangjg
 * 2020/2/20
 */
public abstract class GeneralTree {

    protected List<Integer> traversePath;

    /**
     * 前序遍历
     */
    public void preorder(TreeNode root) {
        if (root != null) {
            this.traversePath.add(root.val);
            this.preorder(root.left);
            this.preorder(root.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inorder(TreeNode root) {
        if (root != null) {
            this.inorder(root.left);
            this.traversePath.add(root.val);
            this.inorder(root.right);
        }
    }

    /**
     * 中序遍历
     */
    public void postorder(TreeNode root) {
        if (root != null) {
            this.postorder(root.left);
            this.postorder(root.right);
            this.traversePath.add(root.val);
        }
    }

}

package com.wangjg.algorithm.tree;

import java.util.*;

/**
 * @author wangjg
 * 2020/2/20
 */
public class GeneralTree {

    /**
     * 前序遍历
     */
    public List<TreeNode> preorder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        this._preorder(root, res);
        return res;
    }

    private void _preorder(TreeNode root, List<TreeNode> traversalPath) {
        if (root != null) {
            traversalPath.add(root);
            this._preorder(root.left, traversalPath);
            this._preorder(root.right, traversalPath);
        }
    }

    /**
     * 中序遍历
     */
    public List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        this._inorder(root, res);
        return res;
    }

    private void _inorder(TreeNode root, List<TreeNode> traversalPath) {
        if (root != null) {
            this._inorder(root.left, traversalPath);
            traversalPath.add(root);
            this._inorder(root.right, traversalPath);
        }
    }

    /**
     * 后序遍历
     */
    public List<TreeNode> postorder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        this._postorder(root, res);
        return res;
    }

    private void _postorder(TreeNode root, List<TreeNode> traversalPath) {
        if (root != null) {
            this._postorder(root.left, traversalPath);
            this._postorder(root.right, traversalPath);
            traversalPath.add(root);
        }
    }

    /**
     * 深度优先遍历
     */
    public List<TreeNode> dfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }

        List<TreeNode> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode tmp = root;
        while (tmp != null || !stack.isEmpty()) {
            while (tmp != null) {
                res.add(tmp);
                stack.addLast(tmp);
                tmp = tmp.left;
            }
            tmp = stack.removeLast().right;
        }

        return res;
    }

    /**
     * 广度优先遍历
     */
    public List<TreeNode> bfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }

        List<TreeNode> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            for(int i = 0; i < queueSize; i++) {
                TreeNode node = queue.removeFirst();
                res.add(node);
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (left != null) {
                    queue.addLast(left);
                }
                if (right != null) {
                    queue.addLast(right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        GeneralTree generalTree = new GeneralTree();
        List<TreeNode> dfs = generalTree.preorder(treeNode1);
        System.out.println(dfs);

        List<TreeNode> bfs = generalTree.bfs(treeNode1);
        System.out.println(bfs);


        List<Object> list = new ArrayList<>();
        Collections.reverse(list);

        System.out.println(list);
    }
}

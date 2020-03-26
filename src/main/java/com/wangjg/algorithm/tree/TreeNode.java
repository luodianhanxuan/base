package com.wangjg.algorithm.tree;

import lombok.ToString;

/**
 * @author wangjg
 * 2020/2/20
 */
@ToString
public class TreeNode {

    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }

}

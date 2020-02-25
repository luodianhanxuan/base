package com.wangjg.algorithm.leetcode.first.ntreelevelorder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangjg
 * 2020/2/21
 */
public class Solution {

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int leveSize = queue.size();
            List<Integer> list = new ArrayList<>(leveSize);
            for (int i = 0; i < leveSize; i++) {
                Node node = queue.removeFirst();
                list.add(node.val);
                List<Node> children = node.children;
                for (Node child : children) {
                    if (child == null) {
                        continue;
                    }
                    queue.add(child);
                }
            }
            res.add(list);
        }
        return res;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.levelOrder(null);
    }
}
package com.wangjg.algorithm.leetcode.second;

import com.sun.tools.javac.util.Assert;

import java.util.*;

/**
 * @author wangjg
 * 2020/2/27
 */
public class Solution {

    static class ClimbStairs {

        public int climbStairs0(int n) {
            if (n <= 2) {
                return n;
            }
            return climbStairs0(n - 1) + climbStairs1(n - 2);
        }

        public int climbStairs1(int n) {
            if (n <= 2) {
                return n;
            }
            int f1 = 1, f2 = 2, f3 = 3;
            for (int i = 0; i < n - 2; i++) {
                f3 = f1 + f2;
                f1 = f2;
                f2 = f3;
            }
            return f3;
        }
    }

    static class GenerateParententhesis {

        public List<String> generateParententhesis(int n) {
            List<String> res = new ArrayList<>();
            doGenerate(0, 0, n, "", res);
            return res;
        }

        private void doGenerate(int left, int right, int n, String s, List<String> res) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }

            if (left < n) {
                doGenerate(left + 1, right, n, s + "(", res);
            }
            if (right < left) {
                doGenerate(left, right + 1, n, s + ")", res);
            }
        }
    }

    static class GroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strings) {
            Map<String, List<String>> map = new HashMap<>();
            for (String string : strings) {
                char[] chars = string.toCharArray();
                Arrays.sort(chars);
                String key = String.valueOf(chars).toLowerCase();

                List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
                list.add(string);
            }


            return new ArrayList<>(map.values());
        }
    }

    static class TreeNode {
        public TreeNode left, right;

        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class LowestCommonAncestor {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root.val == p.val || root.val == q.val) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // 左右两边都找到了，那么就是 root 了
            if (left != null && right != null) {
                return root;
            }

            return left != null ? left : right;
        }
    }

    static class MaxArea {
        /**
         * 枚举所有的面积，找到最大面积
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
         * 从最大宽度开始向中间左右夹逼
         */
        public int maxArea1(int[] heights) {
            int max = 0;
            for (int i = 0, j = heights.length - 1; i < j; ) {
                int minHeight = heights[i] < heights[j] ? heights[i++] : heights[j--];
                int area = minHeight * (j - i + 1);
                max = Math.max(area, max);
            }
            return max;
        }
    }

    static class MoveZero {
        public void moveZero(int[] nums) {
            int notZeroPos = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[notZeroPos] = nums[i];
                    if (notZeroPos != i) {
                        nums[i] = 0;
                    }
                    notZeroPos++;
                }
            }
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }
    }

    static class NtreeLevelOrder {
        public List<List<Integer>> levelOrder(Node root) {
            if (root == null) {
                return new ArrayList<>();
            }

            List<List<Integer>> res = new ArrayList<>();
            Deque<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node node = queue.remove();
                    tmp.add(node.val);
                    // 将其孩子节点加入到队列中
                    List<Node> children = node.children;
                    if (children == null) {
                        continue;
                    }
                    for (Node child : children) {
                        if (child != null) {
                            queue.add(child);
                        }
                    }
                }
                res.add(tmp);
            }

            return res;
        }
    }

    static class TreePreOrderTraversal {

        public List<Integer> preOrder0(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            doPreOrder0(root, res);
            return res;
        }

        private void doPreOrder0(TreeNode root, List<Integer> res) {
            if (root != null) {
                res.add(root.val);
                doPreOrder0(root.left, res);
                doPreOrder0(root.right, res);
            }
        }

        public List<Integer> preOrder1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return new ArrayList<>();
            }

            Deque<TreeNode> stack = new LinkedList<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    res.add(root.val);
                    stack.addLast(root);
                    root = root.left;
                }
                root = stack.removeLast().right;
            }
            return res;
        }
    }

    static class TwoSum {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (target == nums[i] + nums[j]) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[0];
        }
    }

    static class MyPow {
        public double myPow(int x, int n) {
            if (n < 0) {
                x = 1 / x;
                n = -n;
            }
            return fastPow(x, n);
        }

        private double fastPow(int x, int n) {
            if (n == 0) {
                return 1.0;
            }
            double v = fastPow(x, n / 2);
            return n % 2 == 0 ? v * v : v * v * x;
        }
    }


    public static void main(String[] args) {
        System.out.println("======= climbStairs =======");
        ClimbStairs climbStairs = new ClimbStairs();
        long climbStairs0Begin = System.currentTimeMillis();
        int climbStairsRes0 = climbStairs.climbStairs0(30);
        long climbStairs0End = System.currentTimeMillis();
        System.out.println(climbStairsRes0);
        System.out.println("climbStairs0 time spent:" + (climbStairs0End - climbStairs0Begin));
        Assert.check(climbStairsRes0 == 1346269);

        long climbStairs1Begin = System.currentTimeMillis();
        int climbStairsRes1 = climbStairs.climbStairs1(30);
        long climbStairs1End = System.currentTimeMillis();
        System.out.println(climbStairsRes1);
        System.out.println("climbStairs1 time spent:" + (climbStairs1End - climbStairs1Begin));
        Assert.check(climbStairsRes1 == 1346269);

        System.out.println("======= generateParententhesis =======");
        GenerateParententhesis generateParententhesis = new GenerateParententhesis();
        long generateParententhesisBegin = System.currentTimeMillis();
        List<String> generateParententhesisRes = generateParententhesis.generateParententhesis(4);
        long generateParententhesisEnd = System.currentTimeMillis();
        System.out.println(generateParententhesisRes);
        System.out.println("generateParententhesis time spent:" + (generateParententhesisEnd - generateParententhesisBegin));
        Assert.check(generateParententhesisRes.toString().equals(
                "[(((()))), ((()())), ((())()), ((()))(), (()(())), (()()()), (()())(), (())(()), (())()(), ()((())), ()(()()), ()(())(), ()()(()), ()()()()]"
        ));


        System.out.println("======= groupAnagrams =======");
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        long groupAnagramsBegin = System.currentTimeMillis();
        List<List<String>> groupAnagramsRes = groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        long groupAnagramsEnd = System.currentTimeMillis();
        System.out.println(groupAnagramsRes);
        System.out.println("groupAnagrams time spent:" + (groupAnagramsEnd - groupAnagramsBegin));
        Assert.check(groupAnagramsRes.toString().equals(
                "[[eat, tea, ate], [bat], [tan, nat]]"
        ));


        System.out.println("======= lowestCommonAncestor =======");
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        long lowestCommonAncestorBegin = System.currentTimeMillis();
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
        int val = lowestCommonAncestor.lowestCommonAncestor(n1, n2, n9).val;
        long lowestCommonAncestorEnd = System.currentTimeMillis();
        System.out.println(val);
        System.out.println("lowestCommonAncestor time spent:" + (lowestCommonAncestorEnd - lowestCommonAncestorBegin));
        Assert.check(val == 5);

        System.out.println("======= maxArea =======");
        MaxArea maxArea = new MaxArea();
        long maxArea0Begin = System.currentTimeMillis();
        int maxAreaRes0Res = maxArea.maxArea0(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        long maxArea0End = System.currentTimeMillis();
        System.out.println(maxAreaRes0Res);
        System.out.println("maxArea0 time spent:" + (maxArea0End - maxArea0Begin));
        Assert.check(maxAreaRes0Res == 49);

        long maxArea1Begin = System.currentTimeMillis();
        int maxAreaRes1 = maxArea.maxArea1(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        long maxArea1End = System.currentTimeMillis();
        System.out.println(maxAreaRes1);
        System.out.println("maxArea1 time spent:" + (maxArea1End - maxArea1Begin));
        Assert.check(maxAreaRes1 == 49);

        System.out.println("======= moveZero =======");
        MoveZero moveZero = new MoveZero();
        int[] moveZeroRes = new int[]{1, 0, 0, 2, 2};
        long moveZeroBegin = System.currentTimeMillis();
        moveZero.moveZero(moveZeroRes);
        long moveZeroEnd = System.currentTimeMillis();
        System.out.println(Arrays.toString(moveZeroRes));
        System.out.println("groupAnagrams time spent:" + (moveZeroEnd - moveZeroBegin));
        Assert.check(Arrays.toString(moveZeroRes).equals(
                "[1, 2, 2, 0, 0]"
        ));


        System.out.println("======= nTreeLevelOrder =======");
        NtreeLevelOrder ntreeLevelOrder = new NtreeLevelOrder();

        Node levelOrderTreeN1 = new Node(1);
        Node levelOrderTreeN2 = new Node(2);
        Node levelOrderTreeN3 = new Node(3);
        Node levelOrderTreeN4 = new Node(4);
        Node levelOrderTreeN5 = new Node(5);
        Node levelOrderTreeN6 = new Node(6);

        levelOrderTreeN1.children = new ArrayList<Node>() {{
            this.add(levelOrderTreeN3);
            this.add(levelOrderTreeN2);
            this.add(levelOrderTreeN4);
        }};

        levelOrderTreeN3.children = new ArrayList<Node>() {{
            this.add(levelOrderTreeN5);
            this.add(levelOrderTreeN6);
        }};

        long nTreeLevelOrderBegin = System.currentTimeMillis();
        List<List<Integer>> ntreeLevelOrderRes = ntreeLevelOrder.levelOrder(levelOrderTreeN1);
        long nTreeLevelOrderEnd = System.currentTimeMillis();
        System.out.println(ntreeLevelOrderRes);
        System.out.println("ntreeLevelOrder time spent:" + (nTreeLevelOrderEnd - nTreeLevelOrderBegin));
        Assert.check(ntreeLevelOrderRes.toString().equals(
                "[[1], [3, 2, 4], [5, 6]]"
        ));

        System.out.println("======= treePreORderTraversal =======");
        TreePreOrderTraversal treePreOrderTraversal = new TreePreOrderTraversal();
        TreeNode treePreOrderParmN1 = new TreeNode(1);
        TreeNode treePreOrderParmN2 = new TreeNode(2);
        TreeNode treePreOrderParmN3 = new TreeNode(3);
        treePreOrderParmN1.right = treePreOrderParmN2;
        treePreOrderParmN2.left = treePreOrderParmN3;

        long treePreOrderTraversal0Begin = System.currentTimeMillis();
        List<Integer> treePreOrderTraversal0Res = treePreOrderTraversal.preOrder0(treePreOrderParmN1);
        long treePreOrderTraversal0End = System.currentTimeMillis();
        System.out.println(treePreOrderTraversal0Res);
        System.out.println("treePreOrder0Traversal time spent:" + (treePreOrderTraversal0End - treePreOrderTraversal0Begin));
        Assert.check(treePreOrderTraversal0Res.toString().equals(
                "[1, 2, 3]"
        ));

        long treePreOrderTraversal1Begin = System.currentTimeMillis();
        List<Integer> treePreOrderTraversal1Res = treePreOrderTraversal.preOrder1(treePreOrderParmN1);
        long treePreOrderTraversal1End = System.currentTimeMillis();
        System.out.println(treePreOrderTraversal1Res);
        System.out.println("treePreOrder1Traversal time spent:" + (treePreOrderTraversal1End - treePreOrderTraversal1Begin));
        Assert.check(treePreOrderTraversal1Res.toString().equals(
                "[1, 2, 3]"
        ));

        System.out.println("======= twoSum =======");
        TwoSum twoSum = new TwoSum();
        long twoSumBegin = System.currentTimeMillis();
        int[] twoSumRes = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        long twoSumEnd = System.currentTimeMillis();
        System.out.println(Arrays.toString(twoSumRes));
        System.out.println("groupAnagrams time spent:" + (twoSumEnd - twoSumBegin));
        Assert.check(Arrays.toString(twoSumRes).equals(
                "[0, 1]"
        ));

        System.out.println("======= myPow =======");
        MyPow myPow = new MyPow();
        long myPowerBegin = System.currentTimeMillis();
        double myPowerRes = myPow.myPow(5, 4);
        long MyPowerEnd = System.currentTimeMillis();
        System.out.println(myPowerRes);
        System.out.println("myPow time spent:" + (MyPowerEnd - myPowerBegin));
        Assert.check(myPowerRes == 625);

//
//        System.out.println("======= subset =======");
//        Subset subset = new Subset();
//        long subsetBegin = System.currentTimeMillis();
//        List<List<Integer>> subsetRes = subset.subset(new int[]{1, 2, 3});
//        long subsetEnd = System.currentTimeMillis();
//        System.out.println(subsetRes);
//        System.out.println("groupAnagrams time spent:" + (subsetEnd - subsetBegin));
////        Assert.check(Arrays.toString(moveZeroRes).equals(
////                "[1, 2, 2, 0, 0]"
////        ));


/*
先序遍历: EFHIGJK 中序遍历: HFIEJKG
            E
          F


 */

    }
}
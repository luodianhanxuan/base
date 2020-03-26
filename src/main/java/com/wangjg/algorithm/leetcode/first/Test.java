package com.wangjg.algorithm.leetcode.first;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wangjg
 * 2020/3/7
 */
public class Test {

    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        int f0 = 0, f1 = 1, f2;
        for (int i = 0; i < N - 1; i++) {
            f2 = f1 + f0;
            f0 = f1;
            f1 = f2;
        }
        return f1;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseList(ListNode head) {
        return _reverseList(null, head);
    }

    private ListNode _reverseList(ListNode prev, ListNode current) {
        if (current == null) {
            return prev;
        }
        ListNode next = current.next;
        current.next = prev;
        return _reverseList(current, next);
    }


    public static void main(String[] args) {
        Test test = new Test();

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        listNode1.next = listNode2;
        listNode2.next = listNode3;


        ListNode listNode = test.reverseList(listNode1);
        System.out.println(listNode);
    }
}

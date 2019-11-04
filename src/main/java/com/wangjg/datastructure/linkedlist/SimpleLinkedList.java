package com.wangjg.datastructure.linkedlist;

/**
 * @author wangjg
 * 2019/11/4
 */
public class SimpleLinkedList<T> {

    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public boolean hasNext() {
            return this.next != null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private long size;

    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("t cannot be null");
        }
        Node<T> current = new Node<>(t, null);
        if (this.tail == null) {
            this.head = current;
        } else {
            this.tail.next = current;
        }
        this.tail = current;
        this.size++;
        return true;
    }

    public boolean remove(T t) {
        if (t == null) {
            throw new NullPointerException("t cannot be null");
        }
        if (this.head.data.equals(t)) {
            // 删除头操作
        }
        Node<T> prev = this.head;
        boolean findPrev = false;
        while (prev.hasNext()) {
            if (prev.next == null) {
                // 该数据不存在
                break;
            } else {
                if (prev.next.data.equals(t)) {
                    findPrev = true;
                    break;
                }
            }
            prev = prev.next;
        }
        if (findPrev) {
            // 执行删除
        }
        return findPrev;
    }

    public T get(long index) {
        if (head == null) {
            return null;
        }
        if (index >= this.size) {
            throw new IndexOutOfBoundsException(String.format("upper[%s] index[%s]", size - 1, index));
        }
        Node<T> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }
}

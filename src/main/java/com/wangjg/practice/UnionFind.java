package com.wangjg.practice;

/**
 * @author wangjg
 * 2020/7/22
 */
public class UnionFind {

    private int[] ids;

    private int count;

    public UnionFind(int n) {
        ids = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            ids[q] = pRoot;
        }
        count--;
    }

    public int find(int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }

    public boolean connect(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}

package com.wangjg.algorithm.unionfind;

import java.util.Objects;

/**
 * @author wangjg
 * 2020/3/30
 */
@SuppressWarnings("unused")
public class UnionFind {

    /**
     * access to component id (site indexed)
     */
    private int[] id;

    /**
     * number of component
     */
    private int count;

    /**
     * initialize n sites with component id (0 to n-1)
     */
    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * add connection between p and q
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (Objects.equals(pRoot, qRoot)) {
            return;
        }
        id[qRoot] = pRoot;
        count--;
    }

    /**
     * return component identifier for p (0 to n-1)
     */
    public int find(int p) {
        while (id[p] != p) {
            // set the parent node of P node as its grandfather node (push up to achieve the purpose of path compression）
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    /**
     * return true if p and q are in the same  component
     */
    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    /**
     * return number of components
     */
    public int count(){
        return this.count;
    }
}

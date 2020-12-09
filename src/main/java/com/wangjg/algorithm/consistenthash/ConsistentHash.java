package com.wangjg.algorithm.consistenthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangjg
 * 2020/6/8
 */
@SuppressWarnings("unused")
public class ConsistentHash {


    /**
     * 每个真实节点对应的虚拟节点数
     */
    private final int replicationCount;

    /**
     * 真实的节点
     */
    private final List<String> nodes;

    /**
     * 虚拟节点
     */
    private final TreeMap<Long, String> virtualNodes = new TreeMap<>();

    public ConsistentHash(List<String> nodes, int replicCnt) {
        this.nodes = nodes;
        this.replicationCount = replicCnt;
        initialization();
    }

    /**
     * 初始化哈希环
     * 循环计算每个node名称的哈希值，将其放入treeMap
     */
    private void initialization() {
        for (String nodeName : nodes) {
            if (replicationCount <= 4) {
                for (int j = 0; j < 4; j++) {
                    virtualNodes.put(hash(nodeName, j), nodeName);
                }
                continue;
            }
            for (int i = 0; i < replicationCount / 4; i++) {
                String virtualNodeName = getNodeNameByIndex(nodeName, i);
                for (int j = 0; j < 4; j++) {
                    virtualNodes.put(hash(virtualNodeName, j), nodeName);
                }
            }
        }
    }

    private String getNodeNameByIndex(String nodeName, int index) {
        return nodeName +
                "&&" +
                index;
    }

    /**
     * 根据资源key选择返回相应的节点名称
     */
    public String selectNode(String key) {
        Long hashOfKey = hash(key, 0);
        if (!virtualNodes.containsKey(hashOfKey)) {
            Map.Entry<Long, String> entry = virtualNodes.ceilingEntry(hashOfKey);
            if (entry != null) {
                return entry.getValue();
            } else {
                return nodes.get(0);
            }
        } else {
            return virtualNodes.get(hashOfKey);
        }
    }

    private Long hash(String nodeName, int number) {
        byte[] digest = md5(nodeName);
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }

    /**
     * md5加密
     */
    public byte[] md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes(StandardCharsets.UTF_8));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addNode(String node) {
        nodes.add(node);
        if (replicationCount <= 4) {
            for (int j = 0; j < 4; j++) {
                virtualNodes.put(hash(node, j), node);
            }
            return;
        }
        for (int i = 0; i < replicationCount / 4; i++) {
            String virtualNodeName = getNodeNameByIndex(node, i);
            for (int j = 0; j < 4; j++) {
                virtualNodes.put(hash(virtualNodeName, j), node);
            }
        }
    }

    public void removeNode(String node) {
        nodes.remove(node);
        if (replicationCount <= 4) {
            for (int j = 0; j < 4; j++) {
                virtualNodes.remove(hash(node, j), node);
            }
            return;
        }

        for (int i = 0; i < replicationCount / 4; i++) {
            String virtualNodeName = getNodeNameByIndex(node, i);
            for (int j = 0; j < 4; j++) {
                virtualNodes.remove(hash(virtualNodeName, j), node);
            }
        }
    }

    private void printTreeNode() {
        if (!virtualNodes.isEmpty()) {
            virtualNodes.forEach((hashKey, node) ->
                    System.out.println(
                            node + " ==> " + hashKey
                    )
            );
        } else {
            System.out.println("Cycle is Empty");
        }
    }


    public static void main(String[] args) {
        String[] nodes = new String[]{"10.116.18.178", "10.116.18.179", "10.116.18.180"};
        List<String> strings = Arrays.asList(nodes);
        ConsistentHash consistentHash = new ConsistentHash(strings, 100);

        System.out.println(consistentHash.selectNode("23;klj;kjk"));

        consistentHash.printTreeNode();

    }
}

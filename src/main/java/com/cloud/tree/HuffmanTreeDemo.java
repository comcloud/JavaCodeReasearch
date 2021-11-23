package com.cloud.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version v1.0
 * @ClassName HuffmanTreeDemo
 * @Author rayss
 * @Datetime 2021/5/31 12:37 下午
 */

public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node head = createHuffmanTree(arr);
        preOrder(head);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        if(arr == null || arr.length == 0){
            return null;
        }
        //先将数组转换为node 存放到list中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);

            //取出最小权值的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一个新的父节点
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //移除list中处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //新建的节点添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    static class Node implements Comparable<Node> {
        private final int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Node node) {
            //此时表示从小到达，如果
            return this.value - node.value;
        }
    }
}

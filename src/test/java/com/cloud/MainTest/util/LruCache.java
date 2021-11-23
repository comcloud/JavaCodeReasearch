package com.cloud.MainTest.util;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :LruCache.java v1.0 2021/10/8 7:28 下午 zhangyulei Exp $
 */
public class LruCache {
    /**
     * 容量
     */
    private final int capacity;
    /**
     * 数量
     */
    private       int size;

    private Node head;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (head == null) {
            return -1;
        }
        return getVal(key, head);
    }

    private int getVal(int key, Node node) {
        Node resultNode = nodeAt(key, node);
        if (resultNode == null) {
            return -1;
        }
        //将此时这个节点移动到头节点
        if (resultNode != head) {
            replaceHead(resultNode, true);
        }
        return resultNode.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);

        Node nodeAt = nodeAt(key, head);
        //不存在则存放第一个位置
        if (nodeAt == null) {
            //如果已经满了，放置第一个，需要删除最后一个
            if (size >= capacity) {
                //删除最后一个，然后新增节点放到第一个
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                deleteNode(temp);
                //放到第一个
                replaceHead(node, false);
                return;
            }
            if (head == null) {
                head = node;
            } else {
                replaceHead(node, false);
            }
            size++;
        } else {
            //删除此节点，然后将新建的节点放到头节点
            deleteNode(nodeAt);
            replaceHead(node, false);
        }

    }

    private void replaceHead(Node node, boolean isDelete) {
        /*
         * 需要在链表中先移除node节点，然后将node节点放到头节点
         * */
        Node temp = head;
        if (isDelete) {
            while (temp != null) {
                if (temp.key == node.key) {
                    deleteNode(temp);
                }
                temp = temp.next;
            }
        }
        Node n = head;
        head = node;
        node.next = n;
    }

    private void deleteNode(Node node) {
        if (node == null || head == null) {
            return;
        }
        if (head.key == node.key) {
            head = head.next;
            return;
        }
        Node first = head, second = head.next;
        while (second != null) {
            if (second.key == node.key) {
                break;
            }
            first = second;
            second = second.next;
        }
        //1
        if (second != null) {
            first.next = second.next;
        }
    }

    private Node nodeAt(int key, Node node) {
        if (node == null) {
            return null;
        }
        Node temp = node;
        while (temp != null) {
            if (temp.key == key) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    static class Node {
        int  key;
        int  value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

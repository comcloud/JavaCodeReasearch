/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import com.cloud.bean.ListNode;

/**
 * <p>
 * 合并两个有序链表
 * </p>
 * @author zhangyulei
 * @version :CombineLinkedlist.java v1.0 2021/10/23 11:24 上午 zhangyulei Exp $
 */
public class CombineLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);

        ListNode node2 = new ListNode(1);
        ListNode nextNode = new ListNode(2);
        ListNode nextNode2 = new ListNode(4);
        node2.next = nextNode;
        nextNode.next = nextNode2;
        new CombineLinkedList().combine(node1, node2).print();
        System.out.println();

        ListNode l1 = new ListNode(0);
        ListNode next2 = new ListNode(2);
        ListNode next4 = new ListNode(4);
        l1.next = next2;
        next2.next = next4;

        ListNode l2 = new ListNode(1);
        ListNode next3 = new ListNode(3);
        ListNode next5 = new ListNode(5);
        l2.next = next3;
        next3.next = next5;
        ListNode node = new CombineLinkedList().re(l1, l2);
        node.print();

    }

    public ListNode combine(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode first = l1, second = l2;
        ListNode newNode = null;
        ListNode prev = null;
        while (first != null && second != null) {
            if (first.val > second.val) {
                if (newNode == null) {
                    newNode = second;
                    prev = second;
                } else {
                    prev.next = second;
                    prev = prev.next;
                }
                second = second.next;
            } else {
                if (newNode == null) {
                    newNode = first;
                    prev = first;
                } else {
                    prev.next = first;
                    prev = prev.next;
                }
                first = first.next;
            }
        }
        while (first != null) {
            prev.next = first;
            prev = prev.next;
            first = first.next;
        }
        while (second != null) {
            prev.next = second;
            prev = prev.next;
            second = second.next;
        }
        return newNode;

    }

    public ListNode re(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val < l2.val) {
            //直接让l1的下个指向等于其之后合并好的结果
            l1.next = re(l1.next, l2);
            //此时说明合并完成，返回l1就可以，因为此时调整的是l1
            return l1;
        } else {
            //直接让l2的下个指向等于其之后合并好的结果
            l2.next = re(l1, l2.next);
            //此时说明合并完成，返回l2就可以，因为此时调整的是l2
            return l2;
        }
    }

}

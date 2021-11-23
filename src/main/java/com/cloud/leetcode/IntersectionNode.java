/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import com.cloud.bean.ListNode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :IntersectionNode.java v1.0 2021/9/8 6:54 下午 zhangyulei Exp $
 */
public class IntersectionNode {

    public static void main(String[] args) {
        ListNode headA = new ListNode(10);
        ListNode head = new ListNode(9);
        ListNode next1 = new ListNode(1);
        ListNode next2 = new ListNode(2);
        ListNode next3 = new ListNode(4);
        headA.next = head;
        head.next = next1;
        next1.next = next2;
        next2.next = next3;

        ListNode head2 = new ListNode(3);
        head2.next = next1;
        ListNode node = new IntersectionNode().getIntersectionNode(headA, head2);
        if(node != null){
            node.print();
        }else{
            System.out.println("node == null");
        }

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode first = headA, second = headB;
        while(first != second){
            //如果移动到最后还未比较出结果就从对方重新开始，如果确定没有相交，则二者都为空跳出循环
            first = first == null ? headB : first.next;
            second = second == null ? headA : second.next;
        }
        return first;
    }
}

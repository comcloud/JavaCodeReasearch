/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import com.cloud.bean.ListNode;

import java.util.Objects;
import java.util.Stack;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :DeleteNode.java v1.0 2021/9/24 5:22 下午 zhangyulei Exp $
 */
public class DeleteNode {

    public static void main(String[] args) {
//        ListNode head = ListNode.getNormalListNode();
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        head.next = next;
        new DeleteNode().delete(head, 2).print();
    }

    public ListNode removeNthFromEndByStack(ListNode head, int n) {
        if (Objects.isNull(head)) {
            return null;
        }
        // write code here
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int count = 0;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            count++;
            if (count == n) {
                if (stack.isEmpty()) {
                    return node.next;
                }
                ListNode pop = stack.pop();
                pop.next = node.next;
                break;
            }
        }
        return head;
    }

    //前后指针
    public ListNode delete(ListNode head, int n) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode first = head, second = head;
        for (int i = 0; i < n; i++) {
            second = second.next;
        }
        while(second != null && second.next != null){
            first = first.next;
            second = second.next;
        }
        if(second == null){
            head = first.next;
        }else{
            first.next = first.next.next;
        }
        return head;
    }
}

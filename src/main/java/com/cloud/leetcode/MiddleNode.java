/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import com.cloud.bean.ListNode;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :MiddleNode.java v1.0 2021/11/2 9:43 下午 zhangyulei Exp $
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode head = ListNode.getNormalListNode();
        System.out.println(new MiddleNode().middleNode(head).val);
    }

    public ListNode middleNode(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode first = head, second = head;
        while(second.next != null){
            if(second.next.next != null){
                second = second.next.next;
            }else{
                second = second.next;
            }
            first = first.next;
        }
        return first;
    }
}

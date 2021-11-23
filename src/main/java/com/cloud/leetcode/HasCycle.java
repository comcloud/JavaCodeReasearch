/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import com.cloud.bean.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :HasCycle.java v1.0 2021/9/9 7:47 下午 zhangyulei Exp $
 */
public class HasCycle {
    public static void main(String[] args) {
        ListNode head = ListNode.getNormalListNode();
        System.out.println(new HasCycle().hasCycleByMoreSpace(head));
    }

    public boolean hasCycleByMoreSpace(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = head;
        while (temp != null){
            if(set.contains(temp.next)){
                return true;
            }
            set.add(temp);
            temp = temp.next;
        }
        return false;
    }

    public boolean hasCycleByNoMoreSpace(ListNode head){


        return false;
    }
}

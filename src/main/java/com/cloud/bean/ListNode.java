package com.cloud.bean;

import java.util.Objects;

/**
 * @version v1.0
 * @ClassName ListNode
 * @Author rayss
 * @Datetime 2021/8/16 4:41 下午
 */

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }


    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void print(){
        ListNode head = this;
        while(head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }

    public static ListNode getNormalListNode(){
        ListNode headA = new ListNode(10);
        ListNode head = new ListNode(9);
        ListNode next1 = new ListNode(1);
        ListNode next2 = new ListNode(2);
        ListNode next3 = new ListNode(4);
        ListNode next4 = new ListNode(7);
        headA.next = head;
        head.next = next1;
        next1.next = next2;
        next2.next = next3;
        next3.next = next4;
        return headA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}

package com.cloud.MainTest.datastructure;

import java.util.List;

/**
 * @version v1.0
 * @ClassName RemoveDuplicates
 * @Author rayss
 * @Datetime 2021/6/11 12:12 下午
 */

public class RemoveDuplicates {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(3);
        print(head);
        System.out.println();
        ListNode newHead = removeDuplicates(head);
//        ListNode newHead = deleteDuplicates(head);
        print(newHead);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = head;
        while (newHead.next != null) {
            if (newHead.val == newHead.next.val) {
                newHead.next = newHead.next.next;

            } else {
                newHead = newHead.next;
            }
        }
        return head;
    }

    public static ListNode removeDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, curr, next;
        while ((curr = prev.next) != null && (next = curr.next) != null) {
            if (next.val != curr.val) {
                prev = prev.next;
                continue;
            }
            while (next != null && next.val == curr.val) next = next.next;
            prev.next = next;

        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void print(ListNode head) {
        ListNode newHead = head;
        while (newHead != null) {
            System.out.print(newHead.val + "->");
            newHead = newHead.next;
        }
    }
}

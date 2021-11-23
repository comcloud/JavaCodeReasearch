package com.cloud.leetcode;

import com.cloud.bean.ListNode;

import java.util.Stack;

/**
 * @version v1.0
 * @ClassName ReverseList
 * @Author rayss
 * @Datetime 2021/8/16 4:40 下午
 */

public class ReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode next = new ListNode();
        ListNode nextNext = new ListNode();
        ListNode nextNextNext = new ListNode();
        head.next = next;
        next.next = nextNext;
        nextNext.next = nextNextNext;
        head.val = 1;
        next.val = 2;
        nextNext.val = 3;
        nextNextNext.val = 4;

        ListNode newHead = reverseListByIterationProp(head);
        newHead.print();
    }

    public static ListNode reverseListByRecursive(ListNode head) {
        return reverse(null, head);
    }

    private static ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur, next);
    }

    public static ListNode reverseListByIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            stack.push(temp);
            temp = temp.next;
        }
        ListNode newHead = temp;
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return newHead;
    }

    public static ListNode reverseListByIterationProp(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}

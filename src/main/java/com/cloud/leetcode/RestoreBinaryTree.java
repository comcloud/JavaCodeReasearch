package com.cloud.leetcode;

import java.util.Arrays;

/**
 * @version v1.0
 * @ClassName RestoreBianryTree
 * @Author rayss
 * @Datetime 2021/6/9 9:51 下午
 */

public class RestoreBinaryTree {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};
        TreeNode treeNode = constructFromPrePost(pre, post);
        treeNode.midOrder();
        System.out.println();
        treeNode.preOrder();
        System.out.println();
        treeNode.postOrder();
    }

    public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        return helper(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    public static TreeNode helper(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        int index = 0;
        while (post[index] != pre[preStart + 1]) {
            index++;
        }
        root.left = helper(pre, post, preStart + 1, preStart + 1 + index - postStart, postStart, index);
        root.right = helper(pre, post, preStart + 2 + index - postStart, preEnd, index + 1, preEnd - 1);
        return root;

    }
}

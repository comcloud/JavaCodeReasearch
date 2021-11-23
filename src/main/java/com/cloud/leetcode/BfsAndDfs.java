/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Bfs.java v1.0 2021/9/23 10:01 下午 zhangyulei Exp $
 */
public class BfsAndDfs {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode rootLeft = new TreeNode(3);
        TreeNode rootLeftRight = new TreeNode(-1);
        rootLeft.setRight(rootLeftRight);

        TreeNode rootLeftLeft = new TreeNode(1);
        TreeNode rootRight = new TreeNode(7);
        TreeNode rootRightLeft = new TreeNode(6);
        TreeNode rootRightRight = new TreeNode(8);
        root.setLeft(rootLeft);
        rootLeft.setLeft(rootLeftLeft);
        root.setRight(rootRight);
        rootRight.setRight(rootRightRight);
        rootRight.setLeft(rootRightLeft);
        bfs(root);
        System.out.println();
        dfs(root);
    }

    public static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.print(poll.value + "\t");
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    public static void dfs(TreeNode root){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            System.out.print(pop.value + "\t");
            //这里先添加right
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }


    }
}

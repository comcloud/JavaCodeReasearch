/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Dfs.java v1.0 2021/9/16 5:32 下午 zhangyulei Exp $
 */
public class Dfs {

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
        new Dfs().print(root);
        System.out.println();
        new Dfs().printRecursive(root);
    }

    public void print(TreeNode root){
        if(Objects.isNull(root)){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        do{
            TreeNode pop = stack.pop();
            System.out.print(pop.value + "\t");
            if(pop.getRight() != null){
                stack.push(pop.getRight());
            }
            if(pop.getLeft() != null){
                stack.push(pop.getLeft());
            }
        }while (!stack.isEmpty());
    }

    public void printRecursive(TreeNode root){
        if(Objects.isNull(root)){
            return;
        }
        System.out.print(root.value + "\t");
        if(root.getLeft() != null){
            printRecursive(root.getLeft());
        }
        if(root.getRight() != null){
            printRecursive(root.getRight());
        }
    }
}

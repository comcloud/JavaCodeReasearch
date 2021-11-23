package com.cloud.leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * @version v1.0
 * @ClassName TreeNode
 * @Author rayss
 * @Datetime 2021/6/5 4:43 下午
 */

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.value = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.value = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int minHeight() {
        return Math.min(this.left != null ? this.left.minHeight() : 0
                , this.right != null ? this.right.minHeight() : 0) + 1;
    }

    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.print(this.value + "\t");
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public void preOrder() {
        System.out.print(this.value + "\t");
        if (this.left != null) {
            this.left.midOrder();
        }
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        if (this.right != null) {
            this.right.midOrder();
        }
        System.out.print(this.value + "\t");
    }

    public void print(){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(this);
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
}

package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version v1.0
 * @ClassName MiddleTree
 * @Author rayss
 * @Datetime 2021/6/26 7:11 下午
 */

public class MiddleTree {
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

        List<Integer> list = inorderTraversal(root);
        System.out.println(list);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            //将所有左节点压入栈
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            if(!stack.isEmpty()){
                temp = stack.pop();
                list.add(temp.value);
                //让临时结点等于弹出结点的右节点表示下次要进行处理它的右节点了
                temp = temp.right;
            }
        }
        return list;
    }
}

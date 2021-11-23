/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *  二叉树的镜像
 * </p>
 * @author zhangyulei
 * @version :TreeToImage.java v1.0 2021/9/27 8:51 下午 zhangyulei Exp $
 */
public class TreeToImage {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode rootLeft = new TreeNode(3);
        TreeNode rootLeftRight = new TreeNode(-1);
        rootLeft.setRight(rootLeftRight);

        TreeNode rootLeftLeft = new TreeNode(1);
        TreeNode rootRight = new TreeNode(7);
        TreeNode rootRightLeft = new TreeNode(6);
        TreeNode rootRightRight = new TreeNode(-3);
        root.setLeft(rootLeft);
        rootLeft.setLeft(rootLeftLeft);
        root.setRight(rootRight);
        rootRight.setRight(rootRightRight);
        rootRight.setLeft(rootRightLeft);
        TreeNode treeNode = mirrorTree(root);
        treeNode.print();

    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempLeft = root.left;
        root.setLeft(root.right);
        root.setRight(tempLeft);
        if(root.left != null){
            mirrorTree(root.left);
        }
        if(root.right != null){
            mirrorTree(root.right);
        }
        return root;
    }
}

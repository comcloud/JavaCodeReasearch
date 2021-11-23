package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName JudgeTreeBalanced
 * @Author rayss
 * @Datetime 2021/6/22 4:56 下午
 */

public class JudgeTreeBalanced {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootLeftLeft = new TreeNode(3);
        TreeNode rootLeftLeftLeft = new TreeNode(4);
        rootLeftLeftLeft.setLeft(rootLeftLeftLeft);
        rootLeft.setLeft(rootLeftLeft);
        TreeNode rootRight = new TreeNode(2);
        TreeNode rootRightRight = new TreeNode(3);
        TreeNode rootRightRightRight = new TreeNode(4);
        rootRightRight.setRight(rootRightRightRight);
        rootRight.setRight(rootRightRight);
        root.setLeft(rootLeft);
        root.setRight(rootRight);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(!isBalanced(root.left)) return false;
        if(!isBalanced(root.right)) return false;
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1;
    }

    public static int getHeight(TreeNode node){
        if(node == null) return 0;
        return Math.max(node.left == null ? 0:getHeight(node.left),node.right == null ? 0 : getHeight(node.right)) + 1;
    }
}

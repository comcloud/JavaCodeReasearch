package com.cloud.leetcode;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 中序遍历的反插入
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 * @version v1.0
 * @ClassName BuildTree
 * @Author rayss
 * @Datetime 2021/6/29 9:05 下午
 */

public class BuildTree {
    public static void main(String[] args) {
        int[] nums = {42, 5, 25, 24, 5, 34, 5};
        TreeNode node = sortedArrayToBst(nums);
        node.midOrder();
    }

    public static TreeNode sortedArrayToBst(int[] nums) {
        return nums == null ? null : build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middleIndex = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[middleIndex]);
        root.left = build(nums, left, middleIndex - 1);
        root.right = build(nums, middleIndex + 1, right);
        return root;
    }
}

package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName SumOfTreePath
 * @Author rayss
 * @Datetime 2021/7/2 10:54 下午
 */

public class SumOfTreePath {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        List<Integer> list = new ArrayList<>();
        resovle(root, 0, list);
        return list.stream().anyMatch(i -> i == targetSum);
    }

    private void resovle(TreeNode node, int i, List<Integer> list) {
        if (node == null) {
            return;
        }
        i += node.value;
        if (node.left == null && node.right == null) {
            list.add(i);
        }
        resovle(node.left, i, list);
        resovle(node.right, i, list);
    }
}

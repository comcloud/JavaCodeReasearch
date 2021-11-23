/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  找到满足目标值的所有路径
 * </p>
 * @author zhangyulei
 * @version :TreeNodeTarget.java v1.0 2021/9/16 5:43 下午 zhangyulei Exp $
 */
public class TreeNodeTarget {
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
        System.out.println(new TreeNodeTarget().pathSum(root, 8));
    }

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if(Objects.isNull(root)){
            return result;
        }
        temp.add(root.value);
        target -= root.value;
        if(target == 0 && root.left == null && root.right == null){
            result.add(new ArrayList<>(temp));
        }
        pathSum(root.getLeft(),target);
        pathSum(root.getRight(),target);
        //因为当本次递归结束返回上一层的时候，我们已经遍历完这个节点的左右子树，也就是已经该树中可能存在的路径，
        // 再次返回上一层的时候要把这个节点除去，这样在遍历上一个节点的其他子树的时候遍历的结果才是对的
        temp.remove(temp.size() - 1);
        return result;
    }
}

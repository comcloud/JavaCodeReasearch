/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 计算二叉树的最小深度
 * </p>
 * @author zhangyulei
 * @version :MinDepth.java v1.0 2021/10/13 5:04 下午 zhangyulei Exp $
 */
public class MinDepth {

    public static void main(String[] args) {

    }


    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        List<TreeNode> tempList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            tempList.add(pop);
            if(pop.right == null && pop.left == null){
                list.add(tempList.size());
                tempList.clear();
            }
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }
        return list.stream().min(Comparator.comparingInt(o -> o)).get();
    }
}

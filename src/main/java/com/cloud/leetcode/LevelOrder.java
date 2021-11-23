/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.*;

/**
 * <p>
 * 二叉树层序遍历
 * </p>
 * @author zhangyulei
 * @version :LevelOrder.java v1.0 2021/9/16 4:16 下午 zhangyulei Exp $
 */
public class LevelOrder {
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

        System.out.println(new LevelOrder().levelOrder(root));
        System.out.println(level(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(Objects.isNull(root)){
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size > 0){
                TreeNode poll = queue.poll();
                list.add(poll.value);
                if(poll.getLeft()!= null){
                    queue.add(poll.getLeft());
                }
                if(poll.getRight()!=null){
                    queue.add(poll.getRight());
                }
                size--;
            }
            result.add(list);
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> level(TreeNode root){
        if(Objects.isNull(root)){
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        //获取一层的数据
        Queue<TreeNode> queue1 = new LinkedList<>();
        //下一层数据先存储到这个队列，然后上一层处理一次操作之后再把此队列内容移动到queue1
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()) {
            //一次操作
            ArrayList<Integer> list = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode poll = queue1.poll();
                if (poll.left != null) {
                    queue2.offer(poll.left);
                }
                if (poll.right != null) {
                    queue2.offer(poll.right);
                }
                list.add(poll.getValue());
            }
            result.add(list);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        }
        return result;
    }

}

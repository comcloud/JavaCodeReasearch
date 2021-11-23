package com.cloud.tree;

import com.cloud.leetcode.TreeNode;

import java.util.*;

/**
 * DFS,BFS示例
 *
 * @version v1.0
 * @ClassName BfsAndDfsForTree
 * @Author rayss
 * @Datetime 2021/6/10 10:37 下午
 */

public class BfsAndDfsForTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode leftNode = new TreeNode(9);
        leftNode.setLeft(new TreeNode(6));
        leftNode.setRight(new TreeNode(10));
        root.setLeft(leftNode);
        TreeNode rightNode = new TreeNode(20);
        rightNode.setLeft(new TreeNode(15));
        rightNode.setRight(new TreeNode(7));
        root.setRight(rightNode);
        bfs(root);
        dfs(root);
    }


    /**
     * 对于不为空的结点，先把该结点加入到队列中
     * 从队中拿出结点，如果该结点的左右结点不为空，就分别把左右结点加入到队列中
     * 重复以上操作直到队列为空
     */
    private static void bfs(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<TreeNode> treeNodeList = new ArrayList<>();
        queue.add(root);
        do {
            TreeNode node = queue.poll();
            if (node != null && node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if(node != null && node.getRight() != null){
                queue.add(node.getRight());
            }
            treeNodeList.add(node);
        } while (!queue.isEmpty());
        System.out.println(treeNodeList);
    }

    /**
     * 深度优先遍历二叉树
     * 对于不为null的节点添加到栈中
     * 每次从栈中弹出一个元素，如果他的左右节点不为null，则先把右节点添加到栈中，然后将左节点压入栈
     * 反复循环上述过程
     */
    private static void dfs(TreeNode root){
        if (root == null) {
            return;
        }
        List<TreeNode> treeNodeList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null && node.getRight() != null){
                stack.add(node.getRight());
            }
            if (node != null && node.getLeft() != null) {
                stack.add(node.getLeft());
            }
            treeNodeList.add(node);
        }
        System.out.println(treeNodeList);
    }
}

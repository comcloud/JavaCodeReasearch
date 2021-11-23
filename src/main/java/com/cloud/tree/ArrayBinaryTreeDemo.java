package com.cloud.tree;

/**
 * @version v1.0
 * @ClassName ArrayBinaryTree
 * @Author rayss
 * @Datetime 2021/5/30 6:27 下午
 */

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("-------------------------前序遍历--------------------");
        tree.preOrder();
        System.out.println("\n-------------------------中序遍历--------------------");
        tree.middleOrder();
        System.out.println("\n-------------------------后序遍历--------------------");
        tree.postOrder();
    }

    static class ArrayBinaryTree {
        private final int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        public void preOrder() {
            this.preOrder(0);
        }

        /**
         * 顺序存储二叉树通常只考虑二叉树
         * - 第n个元素的左子节点为2 * n + 1
         * - 第n个元素的右子节点为2 * n + 2
         * - 第n个元素的父节点为(n - 1) / 2
         * n:表示二叉树中的第几个元素，从0开始编号，这里我们就认定为他为数组索引值
         * 前序遍历二叉树
         *
         * @param index 数组索引值
         */
        public void preOrder(int index) {
            if (arr == null || arr.length == 0) {
                return;
            }
            System.out.print(arr[index] + "\t");
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
        }

        public void middleOrder() {
            this.middleOrder(0);
        }

        public void middleOrder(int index) {
            if (arr == null || arr.length == 0) {
                return;
            }
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }
            System.out.print(arr[index] + "\t");
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
        }

        public void postOrder(){
            this.postOrder(0);
        }

        public void postOrder(int index){
            if (arr == null || arr.length == 0) {
                return;
            }
            if (2 * index + 1 < arr.length) {
                preOrder(2 * index + 1);
            }
            if (2 * index + 2 < arr.length) {
                preOrder(2 * index + 2);
            }
            System.out.print(arr[index] + "\t");
        }
    }

}

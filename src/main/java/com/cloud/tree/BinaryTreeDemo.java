package com.cloud.tree;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName BinaryTreeDemo
 * @Author rayss
 * @Datetime 2021/5/29 12:13 下午
 */

public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
        tree.setRoot(node1);
        System.out.println("------------------遍历-------------------");
        System.out.println("前序遍历");
        tree.preOrder();
        System.out.println("中序遍历");
        tree.middleOrder();
        System.out.println("后序遍历");
        tree.postOrder();

        System.out.println("------------------查找-------------------");
        System.out.println("前序查找");
        System.out.println(tree.preOrderSearch(2));
        System.out.println("中序查找");
        System.out.println(tree.middleOrderSearch(2));
        System.out.println("后序查找");
        System.out.println(tree.postOrderSearch(2));

        System.out.println("------------------删除-------------------");
        tree.delNode(5);
        tree.preOrder();
    }


    static class BinaryTree {
        private Node root;

        public void setRoot(Node root) {
            this.root = root;
        }

        public void delNode(int no) {
            if (root != null) {
                if (root.no == no) {
                    root = null;
                } else {
                    root.delNode(no);
                }
            } else {
                System.out.println("空树");
            }
        }

        public void preOrder() {
            if (root != null) {
                this.root.preOrder();
            }
        }

        public void middleOrder() {
            if (root != null) {
                this.root.middleOrder();
            }
        }

        public void postOrder() {
            if (root != null) {
                this.root.postOrder();
            }
        }

        public Node preOrderSearch(int no) {
            if (root != null) {
                return this.root.preOrderSearch(no);
            }
            return null;
        }

        public Node middleOrderSearch(int no) {
            if (root != null) {
                return this.root.middleOrderSearch(no);
            }
            return null;
        }

        public Node postOrderSearch(int no) {
            if (root != null) {
                return this.root.postOrderSearch(no);
            }
            return null;
        }
    }

    @Data
    static class Node {
        private int no;
        private String name;
        private Node left;
        private Node right;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }

        /**
         * 如果是叶子节点就删除叶子节点
         * 如果不是叶子节点则直接删除子树
         *
         * @param no 要删除的序号
         */
        public void delNode(int no) {
            /*
             * 1.先判断当前节点的左节点是否是需要删除的节点
             * 2。然后判断右节点是否是需要删除
             * 3。然后递归判断左子树
             * 4。递归判断右子树
             * 注意：不可以直接递归判断，终究是需要有1、2步执行删除的
             * */
            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
            }
            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
            }
            if (this.left != null) {
                this.left.delNode(no);
            }
            if (this.right != null) {
                this.right.delNode(no);
            }
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        public void middleOrder() {
            if (this.left != null) {
                this.left.middleOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.middleOrder();
            }
        }

        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }

        //前序遍历查找
        public Node preOrderSearch(int no) {
            /*
             * 1.先比较当前节点
             * */
            Node res = null;
            if (this.no == no) {
                return this;
            }
            if (this.left != null) {
                res = this.left.preOrderSearch(no);
            }
            //此时说明左节点找到
            if (res != null) {
                return res;
            }
            if (this.right != null) {
                res = this.right.preOrderSearch(no);
            }
            return res;
        }

        //中序遍历查找
        public Node middleOrderSearch(int no) {
            Node res = null;
            if (this.left != null) {
                res = this.left.preOrderSearch(no);
            }
            //此时说明左节点找到
            if (res != null) {
                return res;
            }
            //比较当前节点
            if (this.no == no) {
                return this;
            }
            //比较右节点
            if (this.right != null) {
                res = this.right.preOrderSearch(no);
            }
            return res;
        }

        //后序遍历查找
        public Node postOrderSearch(int no) {
            Node res = null;
            if (this.left != null) {
                res = this.left.preOrderSearch(no);
            }
            //此时说明左节点找到
            if (res != null) {
                return res;
            }
            //比较右节点
            if (this.right != null) {
                res = this.right.preOrderSearch(no);
            }
            //此时说明右节点找到
            if (res != null) {
                return res;
            }
            //比较当前节点
            if (this.no == no) {
                return this;
            }
            return null;
        }
    }
}

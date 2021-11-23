package com.cloud.tree;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName Threaded * @Author rayss
 * @Datetime 2021/5/30 9:15 下午
 */
@SuppressWarnings("all")
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNode();

        //测试
        System.out.println("node4.getLeft() = " + node4.getLeft());
        System.out.println("node4.getRight() = " + node4.getRight());

        tree.threadedList();

    }

    static class ThreadedBinaryTree {
        private Node root;
        private Node pre;

        public void setRoot(Node root) {
            this.root = root;
        }

        public void threadedList() {
            Node node = root;
            while (node != null) {
                while (node.getLeftType() == 0) {
                    node = node.getLeft();
                }
                System.out.println(node);

                while (node.getRightType() == 1) {
                    //获取后继节点，其实这个作用就是回到上个父节点了
                    node = node.getRight();
                    System.out.println(node);
                }
                node = node.getRight();
            }
        }

        public void threadedNode() {
            this.threadedNode(root);
        }

        //线索化节点
        public void threadedNode(Node node) {
            if (node == null) {
                return;
            }
            //先线索化左节点
            threadedNode(node.left);
            //处理当前节点的前驱节点
            if (node.getLeft() == null) {
                node.setLeft(pre);
                node.setLeftType(1);
            }
            //处理后继节点
            if (pre != null && pre.getRight() == null) {
                pre.setRight(node);
                pre.setRightType(1);
            }
            pre = node;
            //线索化右节点
            threadedNode(node.right);
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

        //二者变量作用表示如果type==0，表示left/right指向的是左子树或者右子树，如果type==1，表示left/right指向的是前驱或者后继
        private int leftType;
        private int rightType;

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

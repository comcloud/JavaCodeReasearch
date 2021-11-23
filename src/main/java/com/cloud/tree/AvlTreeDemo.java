package com.cloud.tree;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName AvlTreeDemo
 * @Author rayss
 * @Datetime 2021/6/4 10:33 上午
 */

public class AvlTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AvlTree tree = new AvlTree(arr);
        tree.middleOrder();
        System.out.println("树的高度" + tree.root.height());
        System.out.println("树的左子树高度" + tree.root.leftHeight());
        System.out.println("树的右子树高度" + tree.root.rightHeight());
        tree.middleOrder();
    }

    public static class AvlTree {
        private Node root;

        public AvlTree() {
        }

        public AvlTree(int[] values) {
            if (values != null) {
                for (int value : values) {
                    add(new Node(value));
                }
            }
        }

        public Node getRoot() {
            return root;
        }

        public int delRightTreeMin(Node node) {
            //删除以node为根节点的右子树最小节点，并返回对应的值
            Node target = node.right;
            while (target.left != null) {
                target = target.left;
            }
            delNode(target.value);
            return target.value;
        }

        public void delNode(int value) {
            /*
            这里删除节点要分为三种情况：1.叶子节点；2.非叶子节点有一颗子树；3.非叶子节点有两个子树
            * 1.判断树是否为空，为空直接返回
            * 2.树不为空，判断查找的节点是否存在，不存在直接返回
            * 3.判断树中是否只有根节点，是的话直接让根节点为null
            * 4.获取删除的节点的父节点
            * 5.如果要删除的节点是叶子节点，则判断要删除的节点是左节点还是右节点
            * */
            if (root != null) {
                Node targetNode = root.search(value);
                if (targetNode == null) {
                    //没有找到要删除的节点
                    return;
                }
                if (root.left == null && root.right == null) {
                    //树中只有根节点，同时也找到了要删除的节点，所以要删除的只有根节点
                    root = null;
                    return;
                }
                Node parentNode = root.searchParent(value);
                //此时说明是叶子节点，它的左右节点都为null
                if (targetNode.left == null && targetNode.right == null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        //此时要删除的节点是父节点的左节点
                        parentNode.right = null;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        //此时要删除的节点是父节点的右节点
                        parentNode.left = null;
                    }
                } else if (targetNode.left != null && targetNode.right != null) {
                    //此时说明要删除的节点具有左右子树
                    //同时我们要删除这个节点只需要找到右子树最小节点，然后伤删除并记录值，同时将此值存放到目前要删除的节点中
                    targetNode.value = delRightTreeMin(targetNode);
                } else {
                    //如果父节点为null，说明要删除的是根节点，只需要让根节点指向不为null的左右节点
                    if (parentNode == null) {
                        root = root.left == null ? root.right : root.left;
                        return;
                    }
                    //此时要删除的节点只有左子树或者右子树
                    //要删除的节点具有左子树
                    if (targetNode.left != null) {
                        //判断要删除的节点是父节点的左节点还是右节点
                        if (parentNode.left != null && parentNode.left.value == value) {
                            //此时是左节点
                            parentNode.left = targetNode.left;
                        } else {
                            //此时是右节点
                            parentNode.right = targetNode.left;
                        }
                    }
                    if (targetNode.right != null) {
                        if (parentNode.right != null && parentNode.right.value == value) {
                            parentNode.right = targetNode.right;
                        } else {
                            parentNode.left = targetNode.right;
                        }
                    }
                }
            }
        }

        public Node search(int value) {
            if (root != null) {
                return root.search(value);
            }
            return null;
        }

        public Node searchParent(int value) {
            if (root != null) {
                return root.searchParent(value);
            }
            return null;
        }

        public void add(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
                //此时要判断添加后树是不是已经不满足平衡二叉树
                int difference = rightHeight() - leftHeight();
                if (difference > 1) {
                    //以this.right为根节点，其左子树高度大于右子树高度，需要先进行一次右旋
                    if(root.right != null && root.right.leftHeight() > root.right.rightHeight()){
                        root.right = root.right.rightRotate();
                    }
                    //左旋
                    root = root.leftRotate();
                } else if (difference < -1) {
                    //以this.left为根节点，其右子树高度大于左子树高度，需要先进行一次左旋
                    if(root.left != null && root.left.rightHeight() > root.left.leftHeight()){
                        root.left = root.left.leftRotate();
                    }
                    //右旋
                    root = root.rightRotate();
                }
            }
        }

        private int leftHeight() {
            return root.leftHeight();
        }

        private int rightHeight() {
            return root.rightHeight();
        }

        public void middleOrder() {
            if (root != null) {
                root.middleOrder();
            } else {
                System.out.println("二叉树为空");
            }
        }

        public void leftRotate() {
            root = root.leftRotate();
        }

        public void rightRotate() {
            root = root.rightRotate();
        }
    }

    @Data
    @SuppressWarnings("all")
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int leftHeight() {
            return this.left == null ? 0 : this.left.height();
        }

        public int rightHeight() {
            //这里不进行+1操作是因为，调用方法者不算入高度
            return this.right == null ? 0 : this.right.height();
        }

        public int height() {
            //返回指定子树的高度,+1是因为本身还有一个节点高度
            //同时我们需要知道一个问题，之所以这里可以统计处高度，是因为最后的+1操作，也就是不断的往后查看每次都会进行+1
            return Math.max(this.left == null ? 0 : this.left.height()
                    , this.right == null ? 0 : this.right.height()) + 1;
        }

        public int minHeight() {
            return Math.min(this.left != null ? this.left.minHeight() : 0
                    , this.right != null ? this.right.minHeight() : 0) + 1;
        }

        //左旋方法
        public Node leftRotate() {
            /*
            左旋结果之后会是重新选择根节点来达到目的
            * 1.保存子树根节点的右节点的左节点为tempLeft
            * 2.让子树根节点的右节点左节点指向子树根节点
            * 3.子树根节点不再指向原本的右节点，而是指向原本的右节点的左节点，即tempLeft
            返回新建的根节点
            * */
            Node newRoot = this.right;
            Node tempLeft = this.right.left;
            this.right.left = this;
            this.right = tempLeft;
            return newRoot;
        }

        public void leftRotateTo(){
            Node newNode = new Node(this.value);
            newNode.left = this.left;
            newNode.right = this.right.left;
            this.value = this.right.value;
            this.right = this.right.right;
            this.left = newNode;
        }

        //右旋算法，此时是修改根节点然后返回
        public Node rightRotate() {
            Node newRoot = this.left;
            Node tempRight = this.left.right;
            this.left.right = this;
            this.left = tempRight;
            return newRoot;
        }

        //右旋第二种，不返回修改的根节点，而是新建节点进行更改
        public void rightRotateTo() {
            //新建一个新的节点,节点的值是当前节点的值
            Node newNode = new Node(this.value);
            //将新节点的右子树设置为当前节点的右子树
            newNode.right = this.right;
            //新节点左子树设置为当前节点的左子树的右子树
            newNode.left = this.left.right;
            //把当前节点的值设置为左子节点的值
            this.value = this.left.value;
            //把当前节点的左子树设置为左子树的左子树
            this.left = this.left.left;
            //当前节点右子树设置为新节点
            this.right = newNode;

        }

        public Node search(int value) {
            if (this.value == value) {
                return this;
            } else if (this.value < value) {
                return this.left != null ? this.left.search(value) : null;
            } else {
                return this.right != null ? this.right.search(value) : null;
            }
        }

        public Node searchParent(int value) {
            boolean isParent = (this.left != null && this.left.value == value) || (this.right != null && this.right.value == value);
            if (isParent) {
                return this;
            } else {
                if (this.value < value && this.left != null) {
                    return this.left.searchParent(value);
                } else if (this.right != null) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }

        }

        public void add(Node node) {
            if (node == null) {
                return;
            }

            if (node.value > this.value) {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            } else {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
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
    }
}

package com.cloud.tree;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName BinarySortTreeDemo
 * @Author rayss
 * @Datetime 2021/6/1 11:12 下午
 */

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 5, 65, 6, 5, 6};
        BinarySortTree tree = new BinarySortTree(arr);
        tree.middleOrder();
    }

    public static class BinarySortTree {
        private Node root;

        public BinarySortTree() {
        }

        public BinarySortTree(int[] values) {
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
            }
        }

        public void middleOrder() {
            if (root != null) {
                root.middleOrder();
            } else {
                System.out.println("二叉树为空");
            }
        }
    }


    @Data
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
        public int minHeight() {
            return Math.min(this.left != null ? this.left.minHeight() : 0
                    , this.right != null ? this.right.minHeight() : 0) + 1;
        }
        public int maxHeight(){
            return Math.max(this.left != null ? this.left.maxHeight() : 0
                    , this.right != null ? this.right.maxHeight() : 0) + 1;
        }
    }
}

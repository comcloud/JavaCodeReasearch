/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *     二叉搜索树
 *  寻找给定两个节点的公共祖先
 *  - 如果查找的另一个节点在自己的左右子树中，那么自己就是公共祖先节点
 *  - 如果不在，那么就说明是某个节点的左右节点分别是p以及q
 * </p>
 * @author zhangyulei
 * @version :LowestCommonAncestor.java v1.0 2021/9/25 11:13 上午 zhangyulei Exp $
 */
public class LowestCommonAncestor {

    private TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        /*
         * 1.先遍历p和q，看可以找到对方与否，可以就可以返回，就是第一种情况
         * 2.找不到
         * */
        if (exist(p, q)) {
            return p;
        } else if (exist(q, p)) {
            return q;
        }
        if (exist(root.left, p) && exist(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (exist(root.right, p) && exist(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * p子树下是否存在q
     */
    private boolean exist(TreeNode p, TreeNode q) {
        if (p == null) {
            return false;
        }
        if (p.left == q || p.right == q) {
            return true;
        }
        return exist(p, q);
    }

    public void lca(TreeNode root, TreeNode p, TreeNode q) {
        //结果如果为0，那么root就是p,q其中一个本身，如果小于0，说明正好在左右，这里要知道p,q分别在某个公共祖先节点左右的时候只有一种情况，所以此时满足必定也是root
        if ((root.value - p.value) * (root.value - q.value) <= 0) {
            res = root;
        } else if (root.value < p.value && root.value < q.value) {
            //此时说明p,q都在root的右边
            lca(root.right, p, q);
        } else {
            //查询左边
            lca(root.left, p, q);
        }
    }
}

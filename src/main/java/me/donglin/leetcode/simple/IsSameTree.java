package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 *
 * 示例 2:
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 *
 * 示例 3:
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 * @author donglin
 * @since 2020-08-07
 */
public class IsSameTree {

    @Test
    public void case1() {
        TreeNode p = TreeNode.fullOrder(new Integer[]{1, 2, 3});
        TreeNode q = TreeNode.fullOrder(new Integer[]{1, 2, 3});
        Assert.assertTrue(isSameTree(p, q));
    }

    @Test
    public void case2() {
        TreeNode p = TreeNode.fullOrder(new Integer[]{1, 2});
        TreeNode q = TreeNode.fullOrder(new Integer[]{1, null, 2});
        Assert.assertFalse(isSameTree(p, q));
    }

    @Test
    public void case3() {
        TreeNode p = TreeNode.fullOrder(new Integer[]{1, 2, 1});
        TreeNode q = TreeNode.fullOrder(new Integer[]{1, 1, 2});
        Assert.assertFalse(isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

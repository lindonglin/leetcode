package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * @author donglin
 * @since 2020-08-17
 */
public class IsBalancedTree {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(3, 9, 20, null, null, 15, 7);
        Assert.assertTrue(isBalanced(root));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.fullOrder(1, 2, 2, 3, 3, null, null, 4, 4);
        Assert.assertFalse(isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        return notBalanceOrHeight(root) != -1;
    }

    private int notBalanceOrHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = notBalanceOrHeight(node.left);
        if (lh == -1) {
            return -1;
        }
        int rh = notBalanceOrHeight(node.right);
        if (rh == -1 || Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh) + 1;
    }
}

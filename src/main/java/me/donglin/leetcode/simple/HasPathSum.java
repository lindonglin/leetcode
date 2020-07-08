package me.donglin.leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author donglin
 * @since 2020/07/07
 */
public class HasPathSum {

    @Test
    public void case1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        Assert.assertTrue(hasPathSum(root, 22));
    }


    @Test
    public void case2() {
        Assert.assertFalse(hasPathSum(null, 0));
    }

    @Test
    public void case3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        Assert.assertFalse(hasPathSum(root, 1));
    }

    private boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        int curSum = sum - root.val;
        return hasPathSum(root.left, curSum) || hasPathSum(root.right, curSum);
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

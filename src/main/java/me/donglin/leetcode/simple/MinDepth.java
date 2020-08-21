package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * @author donglin
 * @since 2020-08-21
 */
public class MinDepth {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(3, 9, 20, null, null, 15, 7);
        Assert.assertEquals(2, minDepth(root));
    }

    @Test
    public void case2() {
        TreeNode root = TreeNode.fullOrder(1, 2);
        Assert.assertEquals(2, minDepth(root));
    }

    @Test
    public void case3() {
        TreeNode root = TreeNode.fullOrder(1, null, 2);
        Assert.assertEquals(2, minDepth(root));
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int lDepth = root.left == null ? Integer.MAX_VALUE : minDepth(root.left);
        int rDepth = root.right == null ? Integer.MAX_VALUE : minDepth(root.right);
        return Math.min(lDepth, rDepth) + 1;
    }
}

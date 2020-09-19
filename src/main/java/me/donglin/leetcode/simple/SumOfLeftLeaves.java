package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * @author donglin
 * @since 2020-09-19
 */
public class SumOfLeftLeaves extends BaseTest {


    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    @Override
    public void case1() {
        TreeNode root = TreeNode.fullOrder(3, 9, 20, null, null, 15, 7);
        int expected = 24;
        Assert.assertEquals(expected, sumOfLeftLeaves(root));
    }
}

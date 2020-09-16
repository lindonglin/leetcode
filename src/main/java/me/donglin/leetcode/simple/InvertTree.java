package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * @author donglin
 * @since 2020-09-16
 */
public class InvertTree {

    @Test
    public void case1() {
        TreeNode root = TreeNode.fullOrder(4, 2, 7, 1, 3, 6, 9);
        TreeNode expected = TreeNode.fullOrder(4, 7, 2, 9, 6, 3, 1);
        TreeNode result = invertTree(root);
        Assert.assertEquals(expected, result);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}

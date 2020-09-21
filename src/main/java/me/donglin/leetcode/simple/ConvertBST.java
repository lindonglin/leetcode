package me.donglin.leetcode.simple;

import me.donglin.leetcode.BaseTest;
import me.donglin.leetcode.TreeNode;
import org.junit.Assert;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），
 * 把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 *
 * @author donglin
 * @since 2020-09-21
 */
public class ConvertBST extends BaseTest {

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    @Override
    public void case1() {
        TreeNode root = TreeNode.fullOrder(5, 2, 13);
        TreeNode expected = TreeNode.fullOrder(18, 20, 13);
        Assert.assertEquals(expected, convertBST(root));
    }
}

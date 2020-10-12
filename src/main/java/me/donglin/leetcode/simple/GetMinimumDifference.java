package me.donglin.leetcode.simple;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;


/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 * 提示：
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * @author donglin
 * @since 2020-10-12
 */
public class GetMinimumDifference {

    public int getMinimumDifference(TreeNode root) {
        int min = root.left != null ? root.val - root.left.val : root.right.val - root.val;
        int[] arr = {-root.val - min, min};
        inOrderTraversal(root, arr);
        return arr[1];
    }

    public void inOrderTraversal(TreeNode node, int[] arr) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, arr);
        arr[1] = Math.min(arr[1], node.val - arr[0]);
        arr[0] = node.val;
        inOrderTraversal(node.right, arr);
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(1, null, 3, 2);
        int expected = 1;
        Assert.assertEquals(expected, getMinimumDifference(root));
    }
}

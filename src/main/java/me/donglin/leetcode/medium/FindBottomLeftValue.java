package me.donglin.leetcode.medium;

import me.donglin.leetcode.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 *
 * 输出: 7
 * 提示:
 * 二叉树的节点个数的范围是 [1,10^4]
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * @author donglin
 * @since 2022-06-22
 */
public class FindBottomLeftValue {

    private static final int[] EMPTY = {0, 0};
    public int findBottomLeftValue(TreeNode root) {
        return  findBottomLeftValue(root, 1)[0];
    }

    public int[] findBottomLeftValue(TreeNode root, int height) {
        if (root.left == null && root.right == null) {
            return new int[]{root.val, height};
        }
        int[] left = EMPTY, right = EMPTY;
        if (root.left != null) {
            left = findBottomLeftValue(root.left, height + 1);
        }
        if (root.right != null) {
            right = findBottomLeftValue(root.right, height + 1);
        }
        return left[1] >= right[1] ? left : right;
    }

    @Test
    public void case1() {
        TreeNode root = TreeNode.from(2, 1, 3);
        Assert.assertEquals(1, findBottomLeftValue(root));
    }


    @Test
    public void case2() {
        TreeNode root = TreeNode.from(1, 2, 3, 4, null, 5, 6, null, null, 7);
        Assert.assertEquals(7, findBottomLeftValue(root));
    }

}
